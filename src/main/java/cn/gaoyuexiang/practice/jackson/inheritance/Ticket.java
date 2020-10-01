package cn.gaoyuexiang.practice.jackson.inheritance;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME
)
@JsonSubTypes({
  @JsonSubTypes.Type(value = QuestionTicket.class, name = "QUESTION"),
  @JsonSubTypes.Type(value = BugReportingTicket.class, name = "BUG_REPORTING"),
})
@Data
public abstract class Ticket {
  private TicketType type;
}
