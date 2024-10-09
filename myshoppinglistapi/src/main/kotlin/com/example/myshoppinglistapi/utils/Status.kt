package com.example.myshoppinglistapi.utils

enum class Status {
    INCOMPLETE, COMPLETE
}

enum class TypeFrequencyRepeat(val type: Int) {
    NEVER(-1), ALWAYS(0), ONE_MONTH(1), TWO_MONTH(2), THREE_MONTH(3),
    FOUR_MONTH(4), FIVE_MONTH(5), SIX_MONTH(6), SEVEN_MONTH(7), EIGHT_MONTH(8),
    NINE_MONTH(9), TEN_MONTH(10), ELEVEN_MONTH(11), TWELVE_MONTH(12);

    fun getMonth(): Int {
        return if(this.type == NEVER.type){ 0 } else { this.type + 1 }
    }
}