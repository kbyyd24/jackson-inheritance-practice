package cn.gaoyuexiang.practice.jackson.inheritance;

import lombok.Data;

@Data
public abstract class Ticket {
  private TicketType type;
}
