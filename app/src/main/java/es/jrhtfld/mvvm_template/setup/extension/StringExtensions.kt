package es.jrhtfld.mvvm_template.setup.extension

import java.util.regex.Pattern

private val VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
    "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$",
    Pattern.CASE_INSENSITIVE
)

private val VALID_PASS_REGEX = Pattern.compile(
    "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#€\$%^&*()\\-_=+{}|?>.<,:;~`’]).{8,20})"
)

private val VALID_PHONE_REGEX = Pattern.compile(
    "^(\\+{1}[02-9]{2}([0-9]{9}))|(\\+{1}[1]{1}([0-9]{9}))\$",
    Pattern.CASE_INSENSITIVE
)

private val VALID_BANK_ACCOUNT_REGEX = Pattern.compile(
    "[A-Z]{2}\\d{2} ?\\d{4} ?\\d{4} ?\\d{4} ?\\d{4} ?\\d{4} ?[\\d]{0,2}",
    Pattern.CASE_INSENSITIVE
)

fun String.isEmail(): Boolean {
    val matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(this)
    return matcher.find()
}

fun String.isValidPassword(): Boolean {
    val matcher = VALID_PASS_REGEX.matcher(this)
    return matcher.find()
}

fun String.isPhoneNumber(): Boolean {
    val matcher = VALID_PHONE_REGEX.matcher(this)
    return matcher.find()
}

fun String.isValidBankAccount(): Boolean {
    /*val matcher = VALID_BANK_ACCOUNT_REGEX.matcher(this)
    return matcher.find()*/
    return this.length == 24
}

fun String.isValidDNI(): Boolean {
    var esValido = false
    var i = 0
    var caracterASCII: Int
    val letra: Char
    val miDNI: Int
    val resto: Int
    val asignacionLetra = charArrayOf(
        'T',
        'R',
        'W',
        'A',
        'G',
        'M',
        'Y',
        'F',
        'P',
        'D',
        'X',
        'B',
        'N',
        'J',
        'Z',
        'S',
        'Q',
        'V',
        'H',
        'L',
        'C',
        'K',
        'E'
    )

    if (this.length == 9 && Character.isLetter(this[8])) {

        do {
            caracterASCII = this.codePointAt(i)
            esValido = caracterASCII in 48..57
            i++
        } while (i < this.length - 1 && esValido)
    }

    if (esValido) {
        letra = Character.toUpperCase(this[8])
        miDNI = Integer.parseInt(this.substring(0, 8))
        resto = miDNI % 23
        esValido = letra == asignacionLetra[resto]
    }

    return esValido
}

fun String.addTenths(): String {
    return if (this.toInt() <= 9) {
        String.format("0%s", this)
    } else {
        this
    }
}