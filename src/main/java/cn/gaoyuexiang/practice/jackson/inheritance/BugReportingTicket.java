package cn.gaoyuexiang.practice.jackson.inheritance;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class BugReportingTicket extends Ticket {
  private String productName;
  private String stepToReproduce;
}
