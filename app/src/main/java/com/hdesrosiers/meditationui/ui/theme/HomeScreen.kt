package com.hdesrosiers.meditationui.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hdesrosiers.meditationui.BottomMenuItem
import com.hdesrosiers.meditationui.Feature
import com.hdesrosiers.meditationui.R
import com.hdesrosiers.meditationui.standardQuadFromTo

val chips = listOf("Sweet sleep", "Insomnia", "Depression", "Burnout", "Rebirth")
val roundedCorner = 16.dp

@Composable
fun HomeScreen() {
  Box(
    modifier = Modifier
      .background(DeepBlue)
      .fillMaxSize()
  ) {
    Column {
      GreetingSection()
      Spacer(modifier = Modifier.height(12.dp))
      ChipSection(chips = chips)
      Spacer(modifier = Modifier.height(12.dp))
      CurrentMeditation()
      FeaturedSection(
        features = listOf(
          Feature(
            title = "Sleep meditation",
            R.drawable.ic_headphone,
            BlueViolet1,
            BlueViolet2,
            BlueViolet3
          ),
          Feature(
            title = "Tips for sleeping",
            R.drawable.ic_videocam,
            LightGreen1,
            LightGreen2,
            LightGreen3
          ),
          Feature(
            title = "Night island",
            R.drawable.ic_headphone,
            OrangeYellow1,
            OrangeYellow2,
            OrangeYellow3
          ),
          Feature(
            title = "Calming sounds",
            R.drawable.ic_headphone,
            Beige1,
            Beige2,
            Beige3
          )
        )
      )
    }
    BottomMenu(
      items = listOf(
        BottomMenuItem(iconId = R.drawable.ic_home, title = "Home"),
        BottomMenuItem(iconId = R.drawable.ic_bubble, title = "Meditation"),
        BottomMenuItem(iconId = R.drawable.ic_moon, title = "Sleep"),
        BottomMenuItem(iconId = R.drawable.ic_music, title = "Music"),
        BottomMenuItem(iconId = R.drawable.ic_profile, title = "Profile"),
      ),
      modifier = Modifier.align(BottomCenter)
    )
  }
}

@Composable
fun GreetingSection(name: String = "Dude") {
  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .fillMaxWidth()
      .padding(15.dp)
  ) {
    Column(
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        text = "Good Morning, $name",
        style = MaterialTheme.typography.h2,
        modifier = Modifier.padding(bottom = 8.dp)
      )
      Text(
        text = "We wish you a good day!",
        style = MaterialTheme.typography.body1,
      )
    }
    Icon(
      painter = painterResource(id = R.drawable.ic_search),
      contentDescription = "search",
      tint = Color.White.copy(alpha = 0.7f),
      modifier = Modifier.size(24.dp)
    )
  }
}

@Composable
fun ChipSection(chips: List<String>) {
  var selectedChipIndex by remember { mutableStateOf(0) }

  LazyRow(contentPadding = PaddingValues(start = 15.dp)) {
    itemsIndexed(chips) { i, item ->
      Button(
        onClick = { selectedChipIndex = i },
        colors = ButtonDefaults.buttonColors(
          backgroundColor = if (i == selectedChipIndex) ButtonBlue else DarkerButtonBlue
        ),
        contentPadding = PaddingValues(12.dp),
        shape = RoundedCornerShape(roundedCorner)
      ) {
        Text(
          text = item,
          color = TextWhite
        )
      }
      Spacer(modifier = Modifier.width(10.dp))
    }
  }
}

@Composable
fun CurrentMeditation() {
  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .padding(15.dp)
      .fillMaxWidth()
      .clip(shape = RoundedCornerShape(roundedCorner))
      .background(color = LightRed)
      .padding(vertical = 30.dp, horizontal = 15.dp)
  ) {
    Column(
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        text = "Daily Thought",
        style = MaterialTheme.typography.h2,
        modifier = Modifier.padding(bottom = 8.dp)
      )
      Text(
        text = "Meditation - 3-10 min",
        style = MaterialTheme.typography.body1,
        color = TextWhite
      )
    }
    Box(
      modifier = Modifier
        .clip(shape = CircleShape)
        .size(48.dp)
        .background(color = ButtonBlue),
      contentAlignment = Center
    ) {
      Icon(
        painter = painterResource(id = R.drawable.ic_play),
        contentDescription = "play",
        tint = Color.White,
        modifier = Modifier.size(12.dp)
      )
    }
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeaturedSection(features: List<Feature>) {
  Column(modifier = Modifier.fillMaxWidth()) {
    Text(
      text = "Featured",
      style = MaterialTheme.typography.h1,
      modifier = Modifier.padding(15.dp)
    )
    LazyVerticalGrid(
      cells = GridCells.Fixed(count = 2),
      contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, top = 5.dp, bottom = 100.dp),
      modifier = Modifier.fillMaxHeight()
    ) {
      items(features.size) {
        FeatureItem(feature = features[it])
      }
    }
  }
}

@Composable
fun FeatureItem(
  feature: Feature
) {
  BoxWithConstraints(
    modifier = Modifier
      .padding(7.5.dp)
      .aspectRatio(1f)
      .clip(shape = RoundedCornerShape(14.dp))
      .background(color = feature.darkColor)
  ) {
    val width = constraints.maxWidth
    val height = constraints.maxHeight

    // medium colored path
    val mediumColoredPoint1 = Offset(x = 0f, y = height * 0.3f)
    val mediumColoredPoint2 = Offset(x = width * 0.1f, y = height * 0.35f)
    val mediumColoredPoint3 = Offset(x = width * 0.4f, y = height * 0.05f)
    val mediumColoredPoint4 = Offset(x = width * 0.75f, y = height * 0.7f)
    val mediumColoredPoint5 = Offset(x = width * 1.4f, y = -height.toFloat())

    val mediumColoredPath = Path().apply {
      moveTo(
        x = mediumColoredPoint1.x,
        y = mediumColoredPoint1.y
      )
      standardQuadFromTo(mediumColoredPoint1, mediumColoredPoint2)
      standardQuadFromTo(mediumColoredPoint2, mediumColoredPoint3)
      standardQuadFromTo(mediumColoredPoint3, mediumColoredPoint4)
      standardQuadFromTo(mediumColoredPoint4, mediumColoredPoint5)
      lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
      lineTo(-100f, height.toFloat() + 100f)
      close()
    }

    // light colored path
    val lightColoredPoint1 = Offset(x = 0f, y = height * 0.35f)
    val lightColoredPoint2 = Offset(x = width * 0.1f, y = height * 0.4f)
    val lightColoredPoint3 = Offset(x = width * 0.3f, y = height * 0.35f)
    val lightColoredPoint4 = Offset(x = width * 0.65f, y = height.toFloat())
    val lightColoredPoint5 = Offset(x = width * 1.4f, y = -height.toFloat() / 3f)

    val lightColoredPath = Path().apply {
      moveTo(
        x = lightColoredPoint1.x,
        y = lightColoredPoint1.y
      )
      standardQuadFromTo(lightColoredPoint1, lightColoredPoint2)
      standardQuadFromTo(lightColoredPoint2, lightColoredPoint3)
      standardQuadFromTo(lightColoredPoint3, lightColoredPoint4)
      standardQuadFromTo(lightColoredPoint4, lightColoredPoint5)
      lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
      lineTo(-100f, height.toFloat() + 100f)
      close()
    }
    Canvas(
      modifier = Modifier
        .fillMaxSize()
    ) {
      drawPath(
        path = mediumColoredPath,
        color = feature.mediumColor
      )
      drawPath(
        path = lightColoredPath,
        color = feature.lightColor
      )
    }
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(15.dp)
    ) {
      Text(
        text = feature.title,
        style = MaterialTheme.typography.h2,
        lineHeight = 26.sp,
        modifier = Modifier
          .align(
            TopStart
          )
      )
      Icon(
        painter = painterResource(id = feature.iconId),
        contentDescription = feature.title,
        tint = Color.White,
        modifier = Modifier.align(BottomStart)
      )
      Text(
        text = "Start",
        color = TextWhite,
        fontSize = 14.sp,
        fontWeight = Bold,
        modifier = Modifier
          .align(BottomEnd)
          .clip(RoundedCornerShape(roundedCorner))
          .clickable { }
          .background(color = ButtonBlue)
          .padding(vertical = 6.dp, horizontal = 15.dp)
      )
    }
  }
}

@Composable
fun BottomMenu(
  items: List<BottomMenuItem>,
  modifier: Modifier = Modifier,
  activeSelectionColor: Color = ButtonBlue,
  activeTextColor: Color = Color.White,
  inactiveTextColor: Color = Color.White.copy(alpha = 0.6f),
  initialSelectedItemIndex: Int = 0
) {
  var selectedItemIndex by remember { mutableStateOf(initialSelectedItemIndex) }

  Row(
    horizontalArrangement = Arrangement.SpaceAround,
    verticalAlignment = CenterVertically,
    modifier = modifier
      .fillMaxWidth()
      .background(color = DeepBlue)
      .padding(15.dp)
  ) {
    items.forEachIndexed { index, item ->
      BottomMenuItem(
        item = item,
        isSelected = index == selectedItemIndex,
        activeSelectionColor = activeSelectionColor,
        activeTextColor = activeTextColor,
        inactiveTextColor = inactiveTextColor
      ) {
        selectedItemIndex = index
      }
    }
  }
}

@Composable
fun BottomMenuItem(
  item: BottomMenuItem,
  isSelected: Boolean = false,
  activeSelectionColor: Color = ButtonBlue,
  activeTextColor: Color = Color.White,
  inactiveTextColor: Color = Color.White.copy(alpha = 0.6f),
  onItemClick: () -> Unit
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier.clickable { onItemClick() }
  ) {
    Box(
      contentAlignment = Center,
      modifier = Modifier
        .clip(RoundedCornerShape(roundedCorner))
        .background(
          color = if (isSelected) activeSelectionColor else Color.Transparent
        )
        .padding(10.dp)
    ) {
      Icon(
        painter = painterResource(id = item.iconId),
        contentDescription = item.title,
        tint = if (isSelected) activeTextColor else inactiveTextColor,
        modifier = Modifier.size(20.dp)
      )
    }
    Text(
      text = item.title,
      color = if (isSelected) activeTextColor else inactiveTextColor,
//      style = MaterialTheme.typography.subtitle1
    )
  }
}






































