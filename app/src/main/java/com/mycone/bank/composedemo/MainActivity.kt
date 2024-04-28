package com.mycone.bank.composedemo

import android.os.Bundle
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.jar.Attributes.Name

var realUsers = ArrayList<User>()
var userListState = ArrayList<User>()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Preview(showBackground = true, widthDp = 300, heightDp = 500)
@Composable
fun myOnCreate() {
//    Column {
//        myUserItem(user = User(name = "Krupal", 20, R.drawable.portrait_24))
//        myUserItem(user = User(name = "Rushika", 25, R.drawable.portrait_24))
//        myUserItem(user = User(name = "Nayana", 47, R.drawable.portrait_24))
//        myUserItem(user = User(name = "Navneet", 52, R.drawable.portrait_24))
//    }
    realUsers = initUsers()
    userListState.addAll(realUsers)

    Column {
        dataChanges()
        LazyColumn {
            items(userListState) { user: User ->
//                myUserItem(user = user)
            }
        }
    }
}

@Composable
fun dataChanges() {
    Row {
        var itemStat = remember {
            mutableIntStateOf(0)
        }
        TextField(
            value = itemStat.value.toString(),
            onValueChange = { it ->
                itemStat.value = it.toInt()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            lebel = { Text(text = "Name") },

            )
        Button(onClick = {

        }) {
            Text(text = "Add")
        }
    }
    Row {
        var itemStat = remember {
            mutableIntStateOf(0)
        }
        TextField(value = itemStat.value.toString(), onValueChange = { it ->
            itemStat.value = it.toInt()
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        Button(onClick = {

        }) {
            Text(text = "Add")
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

