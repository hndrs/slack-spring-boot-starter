package io.olaph.slack.broker.autoconfiguration

import io.olaph.slack.broker.receiver.EventReceiver
import io.olaph.slack.broker.receiver.InstallationReceiver
import io.olaph.slack.broker.receiver.InteractiveComponentReceiver
import io.olaph.slack.broker.receiver.MismatchCommandReciever
import io.olaph.slack.broker.receiver.SL4JLoggingReceiver
import io.olaph.slack.broker.receiver.SlashCommandReceiver
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent


class EvaluationReport : ApplicationListener<ContextRefreshedEvent> {

    override fun onApplicationEvent(event: ContextRefreshedEvent) {

        val eventReceiverBeans = event.applicationContext.getBeanNamesForType(EventReceiver::class.java)
        val installationReceiverBeans = event.applicationContext.getBeanNamesForType(InstallationReceiver::class.java)
        val interactiveCompReceiverBeans = event.applicationContext.getBeanNamesForType(InteractiveComponentReceiver::class.java)
        val slashCommandReceiverBeans = event.applicationContext.getBeanNamesForType(SlashCommandReceiver::class.java)
        val loggingReceiverBeans = event.applicationContext.getBeanNamesForType(SL4JLoggingReceiver::class.java)
        val mismatchCommandRecieverBeans = event.applicationContext.getBeanNamesForType(MismatchCommandReciever::class.java)

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
        println(sb)
    }
}