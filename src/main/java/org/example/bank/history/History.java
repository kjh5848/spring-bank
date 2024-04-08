package org.example.bank.history;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bank.account.Account;
import org.example.bank.user.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor()
@Entity
@Table(name = "history_tb")
@Data
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account sendrer; // 보낸이

    @ManyToOne(fetch = FetchType.LAZY)
    private Account receiver; // 받는이

    @Column(nullable = false)
    private Long amount; //이체 금액

    @Column(nullable = false)
    private Long senderBalance; // 보낸이 잔액

    @Column(nullable = false)
    private Long receiverBalance; // 받는이 잔액, 최종잔액 x'

    @CreationTimestamp // pc -> db (날짜주입)
    private LocalDateTime createdAt;

    @Builder
    public History(Long id, Account sendrer, Account receiver, Long amount, Long senderBalance, Long receiverBalance, LocalDateTime createdAt) {
        this.id = id;
        this.sendrer = sendrer;
        this.receiver = receiver;
        this.amount = amount;
        this.senderBalance = senderBalance;
        this.receiverBalance = receiverBalance;
        this.createdAt = createdAt;
    }
}
