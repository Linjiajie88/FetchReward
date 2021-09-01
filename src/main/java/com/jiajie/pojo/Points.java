package com.jiajie.pojo;

import lombok.*;

import javax.websocket.server.ServerEndpoint;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Points {
    @Getter
    @Setter
    private int points;


}
