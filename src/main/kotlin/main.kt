fun main() {
    println(limit(148_000_99, "MasterCard", 0))
    println(limit(2_570_00))
}


fun limit(transfer: Int, cart: String = "VK Pay", transferLast: Int = 0): String {
    when (cart) {
        "MasterCard", "Maestro", "Visa", "Mir" ->
            if ((transferLast + transfer) > 600_000_00) {
                return "Превышен месячный лимит на перевод через банковскую карту"
            } else if (transfer <= 150_000_00) {
                val summCommision = card(transfer, cart, transferLast)
                return "Комиссия составит  ${String.format("%.2f", summCommision)} рублей"
            } else {
                return "Превышен суточный лимит на перевод через банковскую карту"
            }
        else -> if ((transferLast + transfer) > 40_000_00) {
            return "Превышен месячный лимит на перевод через VK Pay"
        } else if (transfer <= 15_000_00) {
            val summCommision = card(transfer, cart, transferLast)
            return "Комиссия составит ${String.format("%.2f", summCommision)} рублей"
        } else return "Превышен суточный лимит на перевод через VK Pay"

    }
}

fun card(transfer: Int, cart: String = "VK Pay", transferLast: Int = 0): Double {
    when (cart) {
        "MasterCard", "Maestro" ->
            if (transferLast + transfer >= 300_00 && transferLast + transfer <= 75000_00) {
                return 0.0
            } else {
                val summCommision = (transfer * 0.006 + 20_00) / 100
                return summCommision
            }
        "Visa", "Mir" -> if (transfer <= 4666_67) {
            return 35.0
        } else {
            val summCommision = (transfer * 0.0075) / 100
            return summCommision
        }
        else -> return 0.0
    }

}


