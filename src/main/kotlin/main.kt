fun main(){
    limit("MasterCard", 200_000_00.0, 0)
}


fun limit(cart: String, transfer: Double, transferLast: Int) {
    when (cart) {
        "MasterCard", "Maestro", "Visa", "Mir" ->
            if ((transferLast + transfer) > 600_000_00)
                println("Превышен месячный лимит на перевод через банковскую карту")
            else if (transfer <= 150_000_00) card(cart, transfer, transferLast)
            else println("Превышен суточный лимит на перевод через банковскую карту")

        else -> if ((transferLast + transfer) > 40_000_00)
            println("Превышен месячный лимит на перевод через VK Pay")
        else if (transfer <= 15_000_00) card(cart, transfer, transferLast)
        else println("Превышен суточный лимит на перевод через VK Pay")

    }
}

fun card(cart: String, transfer: Double, transferLast: Int) {
    when (cart) {
        "MasterCard", "Maestro" -> if (transferLast >= 300_00 && transferLast <= 75000_00)
            println("Комиссия составит 0 рублей")
        else
            println("Комиссия составит ${(transfer * 0.006 + 20_00) / 100} рублей")
        "Visa", "Mir" -> if (transferLast <= 4666_67)
            println("Комиссия составит 35 рублей")
        else
            println("Комиссия составит ${(transfer * 0.0075) / 100}рублей")
        else -> println("Комиссия составит 0 рублей")
    }

}


