import org.junit.Test

import org.junit.Assert.*

class MainKtTest {
    @Test
    fun limitVkPayBigTransfer() {
        val transfer = 15_001_00
        val result = limit(transfer)
        assertEquals("Превышен суточный лимит на перевод через VK Pay", result)
    }

    @Test
    fun limitVkPayBigTransferAndTransferLast() {
        val transfer = 14_000_00
        val transferLast = 39_000_00
        val result = limit(transfer, "VK Pay", transferLast)
        assertEquals("Превышен месячный лимит на перевод через VK Pay", result)
    }

    @Test
    fun limitVkPayNormalTransferAndTransferLast() {
        val transfer = 14_000_00
        val transferLast = 1_000_00
        val result = limit(transfer, "VK Pay", transferLast)
        assertEquals("Комиссия составит ${String.format("%.2f", 0.00)} рублей", result)
    }

    @Test
    fun limitCartBigTransferAndTransferLast() {
        val transfer = 600_000_00
        val transferLast = 1_000_00
        val result = limit(transfer, "Maestro", transferLast)
        assertEquals("Превышен месячный лимит на перевод через банковскую карту", result)
    }

    @Test
    fun limitCartNormalTransferAndTransferLast() {
        val transfer = 50_000_00
        val transferLast = 1_000_00
        val result = limit(transfer, "Maestro", transferLast)
        assertEquals("Комиссия составит  ${String.format("%.2f", 0.00)} рублей", result)
    }

    @Test
    fun limitCartBigTransfer() {
        val transfer = 151_000_00
        val transferLast = 0
        val result = limit(transfer, "Maestro", transferLast)
        assertEquals("Превышен суточный лимит на перевод через банковскую карту", result)
    }

    @Test
    fun limitCartNormalTransfer() {
        val transfer = 150_000_00
        val transferLast = 0
        val result = limit(transfer, "Maestro", transferLast)
        assertEquals("Комиссия составит  ${String.format("%.2f", 920.00)} рублей", result)
    }

    @Test
    fun cardVkPay() {
        val transfer = 15_000_00
        val resualt = card(transfer)
        assertEquals(0.0, resualt, 0.000001)
    }

    @Test
    fun cardMasterCardMin() {
        val transfer = 299_00
        val cart = "Maestro"

        val resualt = card(transfer, cart, transferLast = 0)
        assertEquals(21.794, resualt, 0.000001)
    }

    @Test
    fun cardMasterCard0Procent() {
        val transfer = 300_00
        val cart = "Maestro"

        val resualt = card(transfer, cart, transferLast = 0)
        assertEquals(0.0, resualt, 0.000001)
    }

    @Test
    fun cardVisaMinimum() {
        val transfer = 300_00
        val cart = "Visa"

        val resualt = card(transfer, cart, transferLast = 0)
        assertEquals(35.0, resualt, 0.000001)
    }

    @Test
    fun cardVisaProcent() {
        val transfer = 500_000_00
        val cart = "Mir"

        val resualt = card(transfer, cart, transferLast = 0)
        assertEquals(3750.0, resualt, 0.000001)
    }
}