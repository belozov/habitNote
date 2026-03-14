package com.example.habitnote.view

import android.R.attr.id
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.habitnote.R
import com.example.habitnote.model.Habit
import com.example.habitnote.ui.theme.NoteCyan
import com.example.habitnote.ui.theme.NoteDarkPurple
import com.example.habitnote.ui.theme.NoteGreen
import com.example.habitnote.ui.theme.NoteGrey
import com.example.habitnote.ui.theme.NoteHotPink
import com.example.habitnote.ui.theme.NoteLightGrey
import com.example.habitnote.ui.theme.NoteLightPurple
import com.example.habitnote.ui.theme.NoteOrange
import com.example.habitnote.ui.theme.NotePastelPink
import com.example.habitnote.ui.theme.NotePink
import com.example.habitnote.ui.theme.NoteWhite
import com.example.habitnote.ui.theme.NoteYellow
import com.example.habitnote.viewmodel.MainViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(controller: NavHostController, viewModel: MainViewModel) {
    var isGrid by remember { mutableStateOf(true) }
    var showFilterMenu by remember { mutableStateOf(false) }

    Scaffold(
        Modifier,
        topBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .padding(vertical = 40.dp),
                SpaceBetween,
                Alignment.CenterVertically


            ) {
                Text("Notes", fontWeight = FontWeight.Bold, fontSize = 36.sp )
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Box{
                        Image(
                            painter = painterResource(id = R.drawable.choose_color),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    showFilterMenu = true
                                }
                        )
                        DropdownMenu(
                            expanded = showFilterMenu,
                            { showFilterMenu = false },
                            containerColor = MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(16.dp)
                        ) {
                                FilterScreen()
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))


                    Image(painter =
                        if(isGrid)
                        painterResource(id = R.drawable.menu)
                        else painterResource(id = R.drawable.grid_off),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable {
                                isGrid = !isGrid

                            })
                }


            }
        },
        bottomBar = {
            NavigationBar(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))


            ) {
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                        painter = painterResource(R.drawable.note),
                        contentDescription = null,
                            modifier = Modifier.size(27 .dp)
                    )},
                    label = { Text("Notes", fontSize = 18.sp) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.question_mark),
                            contentDescription = null,
                            modifier = Modifier.size(27.dp)
                        )},
                    label = { Text("Help",fontSize = 18.sp) }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO*/ },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.me),
                            contentDescription = null,
                            modifier = Modifier.size(27.dp)
                        )},
                    label = { Text("Me", fontSize = 18.sp) }
                )





            }

        },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        controller.navigate("add")
                    },
                    Modifier.clip(CircleShape),
                    containerColor = Color(0xffFFB347),
                    elevation = FloatingActionButtonDefaults.elevation(8.dp),

                    ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = null,
                        tint = Color.White
                     )
                }
            }
    )
    {

        Box(
            Modifier.padding(it)

        ){
        if (viewModel.data.value.isEmpty()) {


            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally


            ) {
                Image(painter = painterResource(id = R.drawable.lady), contentDescription = null)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Create your first note !", fontSize = 20.sp)

            }
        }
        if(viewModel.data.value.isNotEmpty()){
            Column(
                Modifier
                    .padding(it)
                    .background(Color.White)
            ) {
                if (!isGrid){
                LazyColumn(
                    Modifier.padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(25.dp)


                ) {
                    items(viewModel.data.value) { data ->
                        ListItem(data, onClick = {
                                controller.navigate("view/$data.id")
                        },
                            onEdit = {
                                controller.navigate("edit/$data.id")
                            } ,
                            onDelete = { viewModel.deleteHabit(data.id)  })
                    }
                    }
                }
                }
                if (isGrid){
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2),
                        Modifier.padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalItemSpacing = 20.dp
                    ){
                        items(viewModel.data.value){ data ->
                            ListItem(data, onClick = {
                                controller.navigate("view/${data.id}")
                            },
                                { controller.navigate("edit/${data.id}")},
                                { viewModel.deleteHabit(data.id) })
                        }


                    }


                }


            }

        }
}
    }




@Composable
fun FilterScreen(){
    val colors = listOf(
        NoteWhite, NotePink, NoteOrange,
        NoteYellow, NoteGreen, NoteCyan,
        NoteHotPink, NoteLightPurple, NoteDarkPurple,
        NotePastelPink, NoteLightGrey, NoteGrey
    )



    Column(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),

    ) {
        Text("Filter by color", fontSize = 24.sp)
        Spacer(Modifier.height(10.dp))
        OutlinedButton(

            onClick = { },
            shape = RoundedCornerShape(30.dp)
        ) {
            Text("Reset", color = Color.Black)
        }
        Spacer(Modifier.height(16.dp))

        val rows = colors.chunked(3)
        rows.forEach { rowColor ->
            Row(
                Modifier.padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                rowColor.forEach { color ->
                    Box(
                        Modifier
                            .weight(1f)
                            .height(40.dp)
                            .width(100.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(color)
                            .border(
                                width = if (color == NoteWhite) 1.dp else 0.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(30.dp)
                            )
                            .clickable {}

                    )
            }

    }
}
    }}



@Composable
fun ListItem(data: Habit, onClick: (Int) -> Unit, onEdit: (Int) -> Unit, onDelete: (Int) -> Unit) {
    var dropDown by remember { mutableStateOf(false) }
    Row (
        Modifier
            .fillMaxWidth()
            .background(data.color, RoundedCornerShape(12.dp))
            .padding(horizontal = 20.dp, vertical = 30.dp)
            .combinedClickable(
                onClick = {
                    onClick(data.id)

                },
                onLongClick = {
                    dropDown = true
                },
            )





    ){
        DropdownMenu(
            expanded = dropDown,
            onDismissRequest = { dropDown = false }
        ){
            DropdownMenuItem(
                text = { Text("Edit") },
                onClick = {
                    dropDown = false
                    onEdit(data.id)
                }
            )
            DropdownMenuItem(
                text = { Text("Delete") },
                onClick = {
                    dropDown = false
                    onDelete(data.id)
                }
            )

        }


        Text(data.title, fontSize = 24.sp)

    }
}

