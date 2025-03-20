package com.untilifoundyou.lostandfound.run;

import java.time.LocalDateTime;

public record Run (Integer id, String title, LocalDateTime startedOn, LocalDateTime finishedOn, Location location){}