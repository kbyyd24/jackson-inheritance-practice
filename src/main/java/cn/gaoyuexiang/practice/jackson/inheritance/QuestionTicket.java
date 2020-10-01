package cn.gaoyuexiang.practice.jackson.inheritance;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class QuestionTicket extends Ticket {
  private String description;
}
