// TeamMapper.kt
package com.android.basketballapp.data.remote.mappers

import com.android.basketballapp.data.Team

fun TeamDto.toDomain(): Team {
    return Team(
        id = id,
        name = name,
        fullName = fullName,
        city = city,
        conference = conference,
        division = division,
        abbreviation = abbreviation
    )
}