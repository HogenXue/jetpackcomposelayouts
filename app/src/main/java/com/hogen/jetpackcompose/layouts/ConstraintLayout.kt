package com.hogen.jetpackcompose.layouts

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.hogen.jetpackcompose.layouts.ui.theme.LayoutsTheme

@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        // Create references for the composables to constrain
        val (button1,button2,text) = createRefs()

        Button(onClick = { /*TODO*/ },
        modifier = Modifier.constrainAs(button1){
            top.linkTo(parent.top,16.dp)
//            centerHorizontallyTo(parent)
        }){
            Text("Button1")
        }

        Text("Text",modifier = Modifier.constrainAs(text) {
            top.linkTo(button1.bottom, 16.dp)
            centerAround(button1.end)
        })

        val barrier = createEndBarrier(button1,text)
        Button(onClick = { /*TODO*/ },
        modifier = Modifier.constrainAs(button2) {
            top.linkTo(parent.top,16.dp)
            start.linkTo(barrier)
        }){
            Text("Button2")
        }

    }
}

@Preview
@Composable
fun ConstraintLayoutContentPreview() {
    LayoutsTheme {
        ConstraintLayoutContent()
    }
}

@Composable
fun LargeConstraintLayout() {
    ConstraintLayout {
        val text = createRef()

        val guideline = createGuidelineFromStart(fraction = 0.5f)
        Text(
            "This is a very very very very very very very long text",
            Modifier.constrainAs(text) {
                linkTo(start = guideline, end = parent.end)
                width= Dimension.preferredWrapContent
            }
        )
    }
}

@Preview
@Composable
fun LargeConstraintLayoutPreview() {
    LayoutsTheme {
        LargeConstraintLayout()
    }
}

@Preview
@Composable
fun DecoupledConstraintLayout(){
    BoxWithConstraints {
        val constrains = if( maxWidth < maxHeight){
            decoupledConstrains(margin = 16.dp)
        }else{
            decoupledConstrains(margin = 32.dp)
        }
        ConstraintLayout(constrains) {
            Button(
                onClick = { /* Do something */ },
                modifier = Modifier.layoutId("button")
            ) {
                Text("Button")
            }
            Text("Text", Modifier.layoutId("text"))
        }
    }
}

@Composable
fun decoupledConstrains(margin:Dp):ConstraintSet{
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button){
            top.linkTo(parent.top,margin)
        }
        constrain(text){
            top.linkTo(button.bottom,margin)
        }
    }
}