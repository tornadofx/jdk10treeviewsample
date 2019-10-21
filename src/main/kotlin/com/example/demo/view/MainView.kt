package com.example.demo.view

import com.example.demo.app.Styles
import javafx.scene.control.TreeItem
import tornadofx.*
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

class MainView : View("Hello TornadoFX") {
    data class Department(val name: String)
    data class Person(val name: String, val department: String)

    val persons = listOf(
            Person("Mary Hanes", "Marketing"),
            Person("Steve Folley", "Customer Service"),
            Person("John Ramsy", "IT Help Desk"),
            Person("Erlick Foyes", "Customer Service"),
            Person("Erin James", "Marketing"),
            Person("Jacob Mays", "IT Help Desk"),
            Person("Larry Cable", "Customer Service")
    )

    val departments = persons.groupBy { Department(it.department) }

    override val root = treeview<Any> {
        root = TreeItem("Departments")
        cellFormat {
            text = when (it) {
                is String -> it
                is Department -> it.name
                is Person -> it.name
                else -> kotlin.error("Invalid value type")
            }
        }
        populate { parent ->
            val value = parent.value
            when {
                parent == root -> departments.keys
                value is Department -> departments[value]
                else -> null
            }
        }
    }
}
