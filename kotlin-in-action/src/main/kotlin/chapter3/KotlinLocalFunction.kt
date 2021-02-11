package chapter3

import java.lang.IllegalArgumentException

/**
 * Created by jyami on 21. 2. 12.
 */
class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user ${user.id} : emtpy $fieldName")
        }
    }
    validate(user.name, "Name")
    validate(user.address, "Address")
}

fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user $id : emtpy $fieldName")
        }
    }
    validate(name, "Name")
    validate(address, "Address")
}

fun saveUser2(user: User) {
    user.validateBeforeSave() // 확장 함수 호출
}