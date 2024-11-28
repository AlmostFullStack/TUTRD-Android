package com.afs.tutrd.core.design_system.component.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.afs.tutrd.core.design_system.theme.AppTypography
import com.afs.tutrd.core.design_system.util.calendar.displayText
import com.afs.tutrd.core.design_system.util.calendar.rememberFirstMostVisibleMonth
import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.OutDateStyle
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.core.yearMonth
import java.time.DayOfWeek
import java.time.LocalDate

const val RANGE_MONTH = 500L

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    selection: LocalDate,
    firstDayOfWeek: DayOfWeek = firstDayOfWeekFromLocale(),
    calendarPadding: Dp = 10.dp,
    onClickSelectButton: (LocalDate) -> Unit,
) {
    val startMonth = remember { currentDate.yearMonth.minusMonths(RANGE_MONTH) }
    val endMonth = remember { currentDate.yearMonth.plusMonths(RANGE_MONTH) }
    val monthState = rememberCalendarState(
        startMonth = startMonth,
        endMonth = endMonth,
        firstVisibleMonth = selection.yearMonth,
        firstDayOfWeek = firstDayOfWeek,
        outDateStyle = OutDateStyle.EndOfGrid,
    )


    MonthCalendar(
        modifier = modifier.padding(horizontal = calendarPadding),
        monthState = monthState,
        selection = selection,
        currentDate = currentDate,
        onChangeDate = { clickedDay ->

        }
    )
}


@Composable
private fun MonthCalendar(
    modifier: Modifier = Modifier,
    monthState: CalendarState,
    currentDate: LocalDate,
    selection: LocalDate,
    onChangeDate: (LocalDate) -> Unit,
) {
    val height = LocalConfiguration.current.screenHeightDp.dp
    HorizontalCalendar(
        state = monthState,
        monthHeader = { CalendarHeader() },
        dayContent = {day ->
            if (day.position != DayPosition.MonthDate) return@HorizontalCalendar
            Day(
                day = day,
                isSelected = selection == day.date,
                isCurrentDate = currentDate == day.date,
            ) { clickedDay ->
                onChangeDate(clickedDay)
            }
        },
        modifier = modifier.heightIn(min = 600.dp),
    )
}

@Stable
@Composable
internal fun CalendarHeader() {
    val daysOfWeek = remember { daysOfWeek() }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        for (dayOfWeek in daysOfWeek) {
            Text(
                modifier = Modifier.weight(1f),
                text = dayOfWeek.displayText(),
                style = AppTypography.Body.b2,
                textAlign = TextAlign.Center
            )
        }
    }
}