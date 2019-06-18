package io.olaph.slack.broker.autoconfiguration

import io.olaph.slack.broker.receiver.EventReceiver
import io.olaph.slack.broker.receiver.InstallationReceiver
import io.olaph.slack.broker.receiver.InteractiveComponentReceiver
import io.olaph.slack.broker.receiver.MismatchCommandReciever
import io.olaph.slack.broker.receiver.SL4JLoggingReceiver
import io.olaph.slack.broker.receiver.SlashCommandReceiver
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.core.Ordered

class EvaluationReport : ApplicationListener<ContextRefreshedEvent>, Ordered {

    override fun getOrder() = Ordered.LOWEST_PRECEDENCE

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        println(buildEvaluationReport(event.applicationContext))
    }

    fun buildEvaluationReport(applicationContext: ApplicationContext): String {
        val eventReceiverBeans = applicationContext.getBeanNamesForType(EventReceiver::class.java)
        val installationReceiverBeans = applicationContext.getBeanNamesForType(InstallationReceiver::class.java)
        val interactiveCompReceiverBeans = applicationContext.getBeanNamesForType(InteractiveComponentReceiver::class.java)
        val slashCommandReceiverBeans = applicationContext.getBeanNamesForType(SlashCommandReceiver::class.java)
        val loggingReceiverBeans = applicationContext.getBeanNamesForType(SL4JLoggingReceiver::class.java)
        val mismatchCommandRecieverBeans = applicationContext.getBeanNamesForType(MismatchCommandReciever::class.java)

        val sb = StringBuilder()
        sb.appendln("+------------------+")
        sb.appendln("| REGISTERED BEANS |")
        sb.appendln("+------------------+\n\n")

        sb.appendln("Event Receiver")
        sb.appendln("--------------\n")
        eventReceiverBeans.forEach {
            sb.appendln("   - $it")
        }
        sb.appendln("\n\nInstallation Receiver")
        sb.appendln("---------------------\n")
        installationReceiverBeans.forEach {
            sb.appendln("   - $it")
        }
        sb.appendln("\n\nInteractive Component Receiver")
        sb.appendln("------------------------------\n")
        interactiveCompReceiverBeans.forEach {
            sb.appendln("   - $it")
        }
        sb.appendln("\n\nSlash Command Receiver")
        sb.appendln("----------------------\n")
        slashCommandReceiverBeans.forEach {
            sb.appendln("   - $it")
        }
        sb.appendln("\n\nLogging Receiver")
        sb.appendln("----------------\n")
        loggingReceiverBeans.forEach {
            sb.appendln("   - $it")
        }
        sb.appendln("\n\nMismatch Command Receiver")
        sb.appendln("-------------------------\n")
        mismatchCommandRecieverBeans.forEach {
            sb.appendln("   - $it")
        }
        return sb.toString()
    }
}