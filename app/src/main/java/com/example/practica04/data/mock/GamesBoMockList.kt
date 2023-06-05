package com.example.practica04.data.mock

import com.example.practica04.model.CompatiblePlatform
import com.example.practica04.model.GameBo
import com.example.practica04.model.Pegi
import java.util.Calendar
import java.util.Date

object GamesBoMockProvider {
    var gameList = listOf(
        GameBo(
            2,
            "Zelda Breath of the wild",
            "Nintendo",
            createDate(2017, 6, 3),
            listOf(CompatiblePlatform.NINTENDO),
            Pegi.PEGI16,
            "https://upload.wikimedia.org/wikipedia/en/c/c6/The_Legend_of_Zelda_Breath_of_the_Wild.jpg"
        ),
        GameBo(
            3,
            "Pokemon amarillo",
            "GameFreak",
            createDate(1998, 4, 30),
            listOf(CompatiblePlatform.NINTENDO),
            Pegi.PEGI7,
            "https://static.wikia.nocookie.net/espokemon/images/9/95/Pok%C3%A9mon_Amarillo.png"
        ),
        GameBo(
            5,
            "Metal gear solid",
            "Konami",
            createDate(1998, 3, 2),
            listOf(CompatiblePlatform.PLAYSTATION),
            Pegi.PEGI18,
            "https://media.vandal.net/m/181/metal-gear-solid-201961220192328_1.jpg"
        ),
        GameBo(
            6,
            "Halo",
            "Bungie",
            createDate(2001, 2, 13),
            listOf(CompatiblePlatform.XBOX),
            Pegi.PEGI18,
            "https://www.liveabout.com/thmb/F4q6V-rm8hOR_g7nOQC4_kDjog0=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/halo-combat-evolved-game-57900ff03df78c09e9a2071e.jpg"
        ),
        GameBo(
            0,
            "GTA V",
            "RockStar",
            createDate(2017, 8, 28),
            listOf(CompatiblePlatform.PLAYSTATION, CompatiblePlatform.XBOX),
            Pegi.PEGI18,
            "https://image.api.playstation.com/cdn/EP1004/CUSA00411_00/eXsWlP0EkcVkLPHgU4pjflmg07252yU8.png"
        ),
        GameBo(
            1,
            "Call of duty 4",
            "Activision",
            createDate(2007, 4, 17),
            listOf(CompatiblePlatform.PLAYSTATION, CompatiblePlatform.XBOX),
            Pegi.PEGI18,
            "https://static.wikia.nocookie.net/cod_esports_gamepedia_en/images/a/a5/Call_of_Duty_4_Game_Cover.jpg"
        ),
        GameBo(
            4,
            "Crash Team Racing",
            "Nintendo",
            createDate(2019, 1, 6),
            listOf(
                CompatiblePlatform.PLAYSTATION,
                CompatiblePlatform.XBOX,
                CompatiblePlatform.NINTENDO
            ),
            Pegi.PEGI7,
            "https://fs-prod-cdn.nintendo-europe.com/media/images/10_share_images/games_15/nintendo_switch_4/H2x1_NSwitch_CrashTeamRacingNitroFueled_image1600w.jpg"
        ),
    )
}

private fun createDate(year: Int, month: Int, day: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.set(year, month - 1, day)
    return calendar.time
}
