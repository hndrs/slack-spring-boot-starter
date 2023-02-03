package io.hndrs.slack.broker.broker

// @DisplayName("Event Broker Tests")
// class EventBrokerTests {
//
//    @Test
//    @DisplayName("Broker Test")
//    fun brokerTest() {
//
//        val teamStore = InMemoryTeamStore()
//
//        val eventStore = InMemoryEventStore()
//
//        teamStore.put(Team.sample().copy(teamId = "TestId"))
//
//        val metrics = EventMetrics()
//
//        val meterRegistry = SimpleMeterRegistry()
//        metrics.bindTo(meterRegistry)
//
//        val successReceiver = SuccessReceiver()
//        val errorReceiver = ErrorReceiver()
//
//        EventBroker(listOf(successReceiver, errorReceiver), teamStore, eventStore, metrics)
//                .receiveEvents(SlackEvent.sample(UserChange.sample())
//                        .copy(teamId = "TestId"), HttpHeaders.EMPTY)
//
//        Assertions.assertTrue(successReceiver.executed)
//        Assertions.assertTrue(errorReceiver.executed)
//
//        Assertions.assertEquals(1.0, meterRegistry.get("slack.events.received").counter().count())
//        Assertions.assertEquals(2.0, meterRegistry.get("slack.events.receiver.executions").counter().count())
//        Assertions.assertEquals(1.0, meterRegistry.get("slack.events.receiver.errors").counter().count())
//    }
//
//    @Test
//    @DisplayName("verify execution order")
//    fun testExecutionOrder() {
//
//    }
//
//    @Test
//    @DisplayName("SlackChallengeTest")
//    fun testSlackChallenge() {
//        val random = UUID.randomUUID().toString()
//        val receiveEvents = EventBroker(listOf(), InMemoryTeamStore(), InMemoryEventStore())
//                .receiveEvents(SlackChallenge.sample().copy(challenge = random), HttpHeaders.EMPTY)
//        Assertions.assertEquals(random, receiveEvents["challenge"])
//    }
//
//
//    @Test
//    @DisplayName("Broker Test Without Metrics")
//    fun brokerTestWithoutMetrics() {
//
//        val teamStore = InMemoryTeamStore()
//
//        val eventStore = InMemoryEventStore()
//
//        teamStore.put(Team.sample().copy(teamId = "TestId"))
//
//        val successReceiver = SuccessReceiver()
//        val errorReceiver = ErrorReceiver()
//
//        EventBroker(listOf(successReceiver, errorReceiver), teamStore, eventStore)
//                .receiveEvents(SlackEvent.sample(UserChange.sample())
//                        .copy(teamId = "TestId"), HttpHeaders.EMPTY)
//
//        Assertions.assertTrue(successReceiver.executed)
//        Assertions.assertTrue(errorReceiver.executed)
//    }
//
//    @Test
//    @DisplayName("Duplicate Event In EventStore")
//    fun duplicateEvent() {
//
//        val teamStore = InMemoryTeamStore()
//
//        val sampleEvent = SlackEvent.sample(UserChange.sample())
//                .copy(teamId = "TestId", eventId = "TestEventId")
//
//        val eventStore = InMemoryEventStore()
//        // Put sampleEvent in teamStore
//        eventStore.put(sampleEvent)
//
//        teamStore.put(Team.sample().copy(teamId = "TestId"))
//
//        val successReceiver = SuccessReceiver()
//        val errorReceiver = ErrorReceiver()
//
//        val listOf = listOf(successReceiver, errorReceiver)
//
//        EventBroker(listOf(successReceiver, errorReceiver), teamStore, eventStore).receiveEvents(sampleEvent, HttpHeaders.EMPTY)
//
//        Assertions.assertFalse(successReceiver.executed)
//        Assertions.assertFalse(errorReceiver.executed)
//    }
//
//    @Test
//    @DisplayName("MustThrow exceptions are thrown at the end of the execution")
//    fun mustThrow() {
//
//        val teamStore = InMemoryTeamStore()
//        teamStore.put(Team.sample().copy(teamId = "TestId"))
//
//        val eventStore = InMemoryEventStore()
//
//
//        val sampleEvent = SlackEvent.sample(UserChange.sample()).copy(teamId = "TestId", eventId = "TestEventId")
//        Assertions.assertThrows(Exception::class.java) {
//            EventBroker(listOf(ShouldThrowReceiver(), ShouldThrowReceiver()), teamStore, eventStore).receiveEvents(sampleEvent, HttpHeaders.EMPTY)
//        }
//    }
//
//    @Test
//    @DisplayName("Test Eventreceiver")
//    fun testEventReceiverExecutionOrder() {
//        val atomic = AtomicInteger(0)
//        val first = FirstEventReceiver(atomic)
//        val second = SecondEventReceiver(atomic)
//        val third = ThirdEventReceiver(atomic)
//        val event = SlackEvent.sample(UserChange.sample()).copy(teamId = "TestTeamId")
//        val store = InMemoryTeamStore()
//        store.put(Team.sample().copy(teamId = "TestTeamId"))
//
//        EventBroker(listOf(third, second, first), store, InMemoryEventStore())
//                .receiveEvents(event, HttpHeaders.EMPTY)
//
//        Assertions.assertEquals(0, first.currentOrder)
//        Assertions.assertEquals(1, second.currentOrder)
//        Assertions.assertEquals(2, third.currentOrder)
//    }
//
//    class FirstEventReceiver(private val current: AtomicInteger) : EventReceiver {
//        override fun order(): Int {
//            return 1
//        }
//
//        var currentOrder: Int? = null
//        override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
//            currentOrder = current.getAndIncrement()
//        }
//    }
//
//    class SecondEventReceiver(private val current: AtomicInteger) : EventReceiver {
//        override fun order(): Int {
//            return 2
//        }
//
//        var currentOrder: Int? = null
//        override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
//            currentOrder = current.getAndIncrement()
//        }
//    }
//
//    class ThirdEventReceiver(private val current: AtomicInteger) : EventReceiver {
//        override fun order(): Int {
//            return 3
//        }
//
//        var currentOrder: Int? = null
//        override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
//            currentOrder = current.getAndIncrement()
//        }
//    }
//
//    class SuccessReceiver : EventReceiver {
//        var executed: Boolean = false
//
//        override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
//            executed = true
//        }
//    }
//
//    class ErrorReceiver : EventReceiver {
//
//        var executed: Boolean = false
//
//        override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
//            executed = true
//            throw IllegalStateException("Failing Test Case")
//        }
//    }
//
//    class ShouldThrowReceiver : EventReceiver {
//        override fun onReceiveEvent(slackEvent: SlackEvent, headers: HttpHeaders, team: Team) {
//            throw Exception()
//        }
//
//        override fun shouldThrowException(exception: Exception): Boolean {
//            return true
//        }
//    }
// }
