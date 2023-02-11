package io.hndrs.slack.broker.autoconfiguration

import io.hndrs.slack.broker.command.CommandHandler
import io.hndrs.slack.broker.command.UnknownCommandHandler
import io.hndrs.slack.broker.event.EventHandler
import io.hndrs.slack.broker.installation.InstallationReceiver
import io.hndrs.slack.broker.store.event.EventStore
import io.hndrs.slack.broker.store.event.InMemoryEventStore
import io.hndrs.slack.broker.store.team.InMemoryTeamStore
import io.hndrs.slack.broker.store.team.TeamStore
import io.hndrs.slack.broker.store.user.UserStore
import org.slf4j.LoggerFactory
import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.core.Ordered
import kotlin.reflect.KClass

/**
 * Class that lists all the auto-registered components
 *
 */
// TODO rework
class EvaluationReport : ApplicationListener<ContextRefreshedEvent>, Ordered {

    override fun getOrder() = Ordered.LOWEST_PRECEDENCE

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        println(buildEvaluationReport(event.applicationContext))
    }

    /**
     * Prints the Evaluation Report to the log
     */
    fun buildEvaluationReport(ctx: ApplicationContext): String {
        val sb = StringBuilder()
        sb.appendLine("+------------------------------------+")
        sb.appendLine("| REGISTERED SLACK BROKER COMPONENTS |")
        sb.appendLine("+------------------------------------+")

        addComponent(
            sb,
            "Event Receivers",
            ctx.getBeanNamesForType(EventHandler::class.java)
        )
        addComponent(
            sb,
            "Installation Receivers",
            ctx.getBeanNamesForType(InstallationReceiver::class.java)
        )
        addComponent(
            sb,
            "Slash Command Receivers",
            ctx.getBeanNamesForType(CommandHandler::class.java)
        )
        addComponent(
            sb,
            "Mismatch Command Receivers",
            ctx.getBeanNamesForType(UnknownCommandHandler::class.java)
        )
        addComponent(
            sb,
            "Team Store",
            ctx.getBeanNamesForType(TeamStore::class.java)
        )
        addComponent(
            sb,
            "User Store",
            ctx.getBeanNamesForType(UserStore::class.java)
        )
        addComponent(
            sb,
            "Event Store",
            ctx.getBeanNamesForType(EventStore::class.java)
        )

        defaultChecks(
            ctx,
            sb,
            Pair(TeamStore::class, InMemoryTeamStore::class),
            Pair(EventStore::class, InMemoryEventStore::class)
        )

        return sb.toString()
    }

    companion object {

        private val LOGGER = LoggerFactory.getLogger(EvaluationReport::class.java)

        private fun addComponent(sb: StringBuilder, title: String, names: Array<out String>) {
            sb.appendLine("\n\n$title")
            sb.appendLine("------------------------------")
            names.forEach {
                sb.appendLine("   - $it")
            }
        }

        private fun defaultChecks(
            ctx: ApplicationContext,
            sb: StringBuilder,
            vararg checks: Pair<KClass<*>, KClass<*>>,
        ) {
            sb.appendLine()
            sb.appendLine("Notes:")
            checks.forEach {
                try {
                    val bean = ctx.getBean(it.first.java)
                    if (it.second.isInstance(bean)) {
                        sb.appendLine(
                            """
                               - Default version of ${it.second.simpleName} is registered,
                                this is not recommended for production
                            """.trimIndent()
                        )
                    }
                } catch (e: BeansException) {
                    LOGGER.error("Error during default implementation check ", e)
                }
            }
        }
    }
}
