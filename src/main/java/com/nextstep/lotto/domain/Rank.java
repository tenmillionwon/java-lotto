package com.nextstep.lotto.domain;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.Arrays;

public enum Rank {
    NOTHING(0, 0L),
    FIFTH(3, 5_000L),
    FOURTH(4, 50_000L),
    THIRD(5, 1_500_000L),
    SECOND(5, 30_000_000L),
    FIRST(6, 2_000_000_000L);

    private final MatchedNumberCount matchedNumberCount;
    private final Money money;

    Rank(final int matchedNumberCount, final long moneyValue) {
      this.matchedNumberCount = new MatchedNumberCount(matchedNumberCount);
      this.money = new Money(moneyValue);
    }

    public static Rank find(final MatchedNumberCount matchedNumberCount, final boolean hasBonus) {
        Rank candidateRank = Arrays.stream(Rank.values())
                .filter(rank -> isSameMatchedNumber(matchedNumberCount, rank))
                .findAny()
                .orElse(NOTHING);
        return confirmRank(candidateRank, hasBonus);
    }

    public Money multiplyPrize(final Long count) {
        return this.money.multiplyCount(count);
    }

    public Long getMoneyValue() {
        return money.getAmount();
    }

    public int getMatchedNumberCount() {
        return matchedNumberCount.getValue();
    }

    private static boolean isSameMatchedNumber(final MatchedNumberCount matchedNumberCount, final Rank rank) {
        return rank.matchedNumberCount.equals(matchedNumberCount);
    }

    private static Rank confirmRank(final Rank candidateRank, final boolean hasBonus) {
        if (candidateRank == Rank.THIRD || candidateRank == Rank.SECOND) {
            return judgeIsSecond(hasBonus);
        }
        return candidateRank;
    }

    private static Rank judgeIsSecond(final boolean hasBonus) {
        if (hasBonus) {
            return Rank.SECOND;
        }
        return Rank.THIRD;
    }
}