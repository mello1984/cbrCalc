package mello.cbrcalc.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "t_log")
public class LoggingEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "date")
    LocalDateTime localDateTime;

    @Column(name = "type")
    LoggingType loggingType;

    @Column(name = "user_name")
    String username;

    @Column(name = "event")
    String event;

    public LoggingEvent(LocalDateTime localDateTime, LoggingType loggingType, String username, String event) {
        this.localDateTime = localDateTime;
        this.loggingType = loggingType;
        this.username = username;
        this.event = event;
    }
}
