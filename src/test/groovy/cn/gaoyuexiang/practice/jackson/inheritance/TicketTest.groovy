package cn.gaoyuexiang.practice.jackson.inheritance

import spock.lang.Specification
import spock.lang.Unroll
import spock.lang.Shared
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException

class TicketTest extends Specification {
  @Shared
  def questionTicketJson = '{"type":"QUESTION","description":"some description"}'
  @Shared
  def bugReportingTicketJson = '{"type":"BUG_REPORTING","productName":"RDS","stepToReproduce":"1.2.3."}'
  @Shared
  def objectMapper = new ObjectMapper()

  def "should serialize question ticket to json"() {
    given:
    def ticket = new QuestionTicket()
    ticket.type = TicketType.QUESTION
    ticket.description = "some description"
    
    when:
    def json = objectMapper.writeValueAsString(ticket)

    then:
    json == questionTicketJson
  }

  def "should serialize bug reporting ticket to json"() {
    given:
    def ticket = new BugReportingTicket()
    ticket.type = TicketType.BUG_REPORTING
    ticket.productName = 'RDS'
    ticket.stepToReproduce = '1.2.3.'

    when:
    def json = objectMapper.writeValueAsString(ticket)

    then:
    json == bugReportingTicketJson
  }

  @Unroll
  def "should throw exception when try to desrialize question json to abstract class"(json) {
    when:
    objectMapper.readValue(json, Ticket.class)

    then:
    def exception = thrown(InvalidDefinitionException)
    exception.message.contains 'Cannot construct instance of `cn.gaoyuexiang.practice.jackson.inheritance.Ticket` (no Creators, like default constructor, exist): abstract types either need to be mapped to concrete types, have custom deserializer, or contain additional type information'

    where:
    json                   | _
    questionTicketJson     | _
    bugReportingTicketJson | _
  }
}
