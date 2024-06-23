package com.mycone.bank.composedemo

import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mycone.bank.composedemo.kpDataStructure.Lister
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.jar.Attributes.Name

var realUsers = ArrayList<User>()
var userListState = Lister<User>()

var stateName = ""
var stateAge = 0

val snackbarHostState = SnackbarHostState()

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inits()
        setContent {
            myOnCreate()
        }
    }

    private fun inits() {
        userListState.listener = object : Lister.ListerListener {
            override fun onAdd() {
                CoroutineScope(Dispatchers.Main).launch {
                    snackbarHostState.showSnackbar(
                        message = "New user added!",
                        duration = SnackbarDuration.Short
                    )
                }
            }

            override fun onAddError() {
                CoroutineScope(Dispatchers.Main).launch {
                    snackbarHostState.showSnackbar(
                        message = "Please enter name and age!",
                        duration = SnackbarDuration.Short
                    )
                }
            }

            override fun onUpdate() {

            }
        }
    }
}

//@Preview(showBackground = true, device = "id:pixel_3")
@Composable
fun myOnCreate() {
//    realUsers = initUsers()
    userListState.addAll(realUsers)

    Column {
        dataChangesScreen()
        LazyColumn {
            items(userListState) { user: User ->
                myUserItem(user = user)
            }
        }
    }
}

@Composable
fun dataChangesScreen() {
    Column {
        Row {
            TextField(
                value = stateName,
                onValueChange = { newValue ->
                    stateName = newValue
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                label = { Text("Name") }
            )
        }

        Row {
            TextField(
                value = stateAge.toString(),
                onValueChange = { newValue ->
                    stateAge = newValue.toInt()
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Age") }
            )

        }

        Button(onClick = {
            val name = stateName.toString()
            val age = stateAge.toInt()

            if (name.isNotEmpty() && age > 0) {
                val user = User(name, age, R.drawable.portrait_24)
                userListState.add(user)
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    snackbarHostState.showSnackbar(
                        message = "Please enter name and age!",
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }) {
            Text("Add")
        }
    }

}


@Composable
fun initUsers(): ArrayList<User> {
    var users = listOf(
        User(name = "Krupal", 20, R.drawable.portrait_24),
        User(name = "Rushika", 25, R.drawable.portrait_24),
        User(name = "Nayana", 47, R.drawable.portrait_24),
        User(name = "Navneet", 52, R.drawable.portrait_24)
    )

    return ArrayList<User>(users)
}

@Composable
fun myUserItem(user: User) {
    Row(Modifier.padding(5.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(user.dpPath),
            contentDescription = "",
            Modifier.size(50.dp)
        )
        Column(Modifier.padding(start = 7.dp)) {
            Text(
                text = user.name, fontFamily = FontFamily.Serif, fontWeight = FontWeight.Bold
            )
            Text(
                text = user.userId.toString(),
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

