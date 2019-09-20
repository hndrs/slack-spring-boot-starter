package com.kreait.slack.broker.autoconfiguration

import com.kreait.slack.broker.receiver.EventReceiver
import com.kreait.slack.broker.receiver.InstallationReceiver
import com.kreait.slack.broker.receiver.InteractiveComponentReceiver
import com.kreait.slack.broker.receiver.MismatchCommandReciever
import com.kreait.slack.broker.receiver.SlashCommandReceiver
import com.kreait.slack.broker.store.event.EventStore
import com.kreait.slack.broker.store.event.InMemoryEventStore
import com.kreait.slack.broker.store.team.InMemoryTeamStore
import com.kreait.slack.broker.store.team.TeamStore
import com.kreait.slack.broker.store.user.UserStore
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.core.Ordered
import kotlin.reflect.KClass

/**
 * Class that lists all the auto-registered components
 *
 */
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
        sb.appendln("+------------------------------------+")
        sb.appendln("| REGISTERED SLACK BROKER COMPONENTS |")
        sb.appendln("+------------------------------------+")

        addComponent(sb, "Event Receivers", ctx.getBeanNamesForType(EventReceiver::class.java));
        addComponent(sb, "Installation Receivers", ctx.getBeanNamesForType(InstallationReceiver::class.java));
        addComponent(sb, "Interactive Component Receivers", ctx.getBeanNamesForType(InteractiveComponentReceiver::class.java));
        addComponent(sb, "Slash Command Receivers", ctx.getBeanNamesForType(SlashCommandReceiver::class.java));
        addComponent(sb, "Mismatch Command Receivers", ctx.getBeanNamesForType(MismatchCommandReciever::class.java));
        addComponent(sb, "Team Store", ctx.getBeanNamesForType(TeamStore::class.java));
        addComponent(sb, "User Store", ctx.getBeanNamesForType(UserStore::class.java));
        addComponent(sb, "Event Store", ctx.getBeanNamesForType(TeamStore::class.java));

        defaultChecks(ctx, sb, Pair(TeamStore::class, InMemoryTeamStore::class), Pair(EventStore::class, InMemoryEventStore::class))

        return sb.toString()
    }

    companion object {

        private fun addComponent(sb: StringBuilder, title: String, names: Array<out String>) {
            sb.appendln("\n\n$title")
            sb.appendln("------------------------------")
            names.forEach {
                sb.appendln("   - $it")
            }
        }

        private fun defaultChecks(ctx: ApplicationContext, sb: StringBuilder, vararg checks: Pair<KClass<*>, KClass<*>>) {
            sb.appendln()
            sb.appendln("Notes:")
            checks.forEach {
                try {
                    val bean = ctx.getBean(it.first.java)
                    if (it.second.isInstance(bean)) {
                        sb.appendln("   - Default version of ${it.second.simpleName} is registered, this is not recommended for production")
                    }
                } catch (e: Exception) {
                    //do nothing
                }
            }

        }

    }

}
