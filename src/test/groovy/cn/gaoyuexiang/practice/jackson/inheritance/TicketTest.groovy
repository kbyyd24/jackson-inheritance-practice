package cn.gaoyuexiang.practice.jackson.inheritance

import spock.lang.Specification
import spock.lang.Unroll
import spock.lang.Shared
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException

class TicketTest extends Specification {
  @Shared
  def questionTicketJson = '{"@class":"cn.gaoyuexiang.practice.jackson.inheritance.QuestionTicket","type":"QUESTION","description":"some description"}'
  @Shared
  def bugReportingTicketJson = '{"@class":"cn.gaoyuexiang.practice.jackson.inheritance.BugReportingTicket","type":"BUG_REPORTING","productName":"RDS","stepToReproduce":"1.2.3."}'
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
  def "should desrialize json to abstract class"(json, type) {
    when:
    def ticket = objectMapper.readValue(json, Ticket.class)

    then:
    notThrown(InvalidDefinitionException)
    type.isCase(ticket)

    where:
    json                   | type
    questionTicketJson     | QuestionTicket
    bugReportingTicketJson | BugReportingTicket
  }
}
