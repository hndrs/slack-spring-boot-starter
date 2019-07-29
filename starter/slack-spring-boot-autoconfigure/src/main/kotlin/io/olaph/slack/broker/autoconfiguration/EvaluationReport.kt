package io.olaph.slack.broker.autoconfiguration

import io.olaph.slack.broker.receiver.EventReceiver
import io.olaph.slack.broker.receiver.InstallationReceiver
import io.olaph.slack.broker.receiver.InteractiveComponentReceiver
import io.olaph.slack.broker.receiver.MismatchCommandReciever
import io.olaph.slack.broker.receiver.SlashCommandReceiver
import io.olaph.slack.broker.store.TeamStore
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.core.Ordered

class EvaluationReport : ApplicationListener<ContextRefreshedEvent>, Ordered {

    override fun getOrder() = Ordered.LOWEST_PRECEDENCE

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        println(buildEvaluationReport(event.applicationContext))
    }

    fun buildEvaluationReport(ctx: ApplicationContext): String {
        val sb = StringBuilder()
        sb.appendln("+------------------+")
        sb.appendln("| REGISTERED SLACK BROKER COMPONENTS |")
        sb.appendln("+------------------+\n\n")

        this.addComponent(sb, "Event Receivers", ctx.getBeanNamesForType(EventReceiver::class.java));
        this.addComponent(sb, "Installation Receivers", ctx.getBeanNamesForType(InstallationReceiver::class.java));
        this.addComponent(sb, "Interactive Component Receivers", ctx.getBeanNamesForType(InteractiveComponentReceiver::class.java));
        this.addComponent(sb, "Slash Command Receivers", ctx.getBeanNamesForType(SlashCommandReceiver::class.java));
        this.addComponent(sb, "Mismatch Command Receivers", ctx.getBeanNamesForType(MismatchCommandReciever::class.java));
        this.addComponent(sb, "Team Store", ctx.getBeanNamesForType(TeamStore::class.java));
        this.addComponent(sb, "Event Store", ctx.getBeanNamesForType(TeamStore::class.java));

        //TODO add default components check to report when default components are used

        return sb.toString()
    }

    private fun addComponent(sb: StringBuilder, title: String, names: Array<out String>) {
        sb.appendln("\n\n$title")
        sb.appendln("------------------------------\n")
        names.forEach {
            sb.appendln("   - $it")
        }
    }
}
