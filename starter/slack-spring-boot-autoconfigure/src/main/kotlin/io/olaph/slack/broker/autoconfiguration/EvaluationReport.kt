package io.olaph.slack.broker.autoconfiguration

import io.olaph.slack.broker.receiver.EventReceiver
import io.olaph.slack.broker.receiver.InstallationReceiver
import io.olaph.slack.broker.receiver.InteractiveComponentReceiver
import io.olaph.slack.broker.receiver.MismatchCommandReciever
import io.olaph.slack.broker.receiver.SlashCommandReceiver
import io.olaph.slack.broker.store.EventStore
import io.olaph.slack.broker.store.InMemoryEventStore
import io.olaph.slack.broker.store.InMemoryTeamStore
import io.olaph.slack.broker.store.TeamStore
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.core.Ordered
import kotlin.reflect.KClass

class EvaluationReport : ApplicationListener<ContextRefreshedEvent>, Ordered {

    override fun getOrder() = Ordered.LOWEST_PRECEDENCE

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        println(buildEvaluationReport(event.applicationContext))
    }

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
