package cn.gaoyuexiang.practice.jackson.inheritance;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.MINIMAL_CLASS
)
@Data
public abstract class Ticket {
  private TicketType type;
}
