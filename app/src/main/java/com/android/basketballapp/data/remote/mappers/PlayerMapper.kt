package com.android.basketballapp.data.remote.mappers

import com.android.basketballapp.data.Player

fun PlayerDto.toDomain(): Player {
    return Player(
        id = id,
        fullName = "$first_name $last_name",
        position = position ?: "Unknown",
        height = if (height_feet != null && height_inches != null) "$height_feet'$height_inches\"" else "N/A",
        weight = weight_pounds?.toString() ?: "N/A",
        teamName = team.fullName
    )
}
