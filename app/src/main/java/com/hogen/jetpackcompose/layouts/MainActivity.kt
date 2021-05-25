package com.hogen.jetpackcompose.layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.hogen.jetpackcompose.layouts.ui.theme.LayoutsTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    BodContent()
                }
            }
        }
    }
}



@Composable
fun ScollList(){
    val listsize = 100
    val scollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()


    Column{
        Row{
            Button(onClick = {
                coroutineScope.launch {
                    scollState.animateScrollToItem(0)
                }
            }) {
                Text("Scroll to Top")
            }
            Button(onClick = {
                coroutineScope.launch {
                    scollState.animateScrollToItem(listsize-1)
                }
            }) {
                Text("Scroll to End")
            }
        }
        LazyColumn(state = scollState) {
            items(listsize){
                ImageListItem(it)
            }
        }
    }
}

@Composable
fun ImageListItem(index:Int){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(painter = rememberCoilPainter(
            request = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp))
        Spacer(Modifier.width(10.dp))
        Text("Item $index",style = MaterialTheme.typography.subtitle1)
    }
}
