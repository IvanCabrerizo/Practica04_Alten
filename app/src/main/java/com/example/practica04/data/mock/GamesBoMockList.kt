package com.example.practica04.data.mock

import com.example.practica04.model.CompatiblePlatform
import com.example.practica04.model.GameBo
import com.example.practica04.model.Pegi

object GamesBoMockProvider {
    val gameList = listOf(
        GameBo(
            2,
            "Zelda Breath of the wild",
            "Nintendo",
            2017,
            listOf(CompatiblePlatform.NINTENDO),
            Pegi.PEGI16,
            "https://upload.wikimedia.org/wikipedia/en/c/c6/The_Legend_of_Zelda_Breath_of_the_Wild.jpg"
        ),
        GameBo(
            3,
            "Pokemon amarillo",
            "GameFreak",
            1998,
            listOf(CompatiblePlatform.NINTENDO),
            Pegi.PEGI7,
            "https://static.wikia.nocookie.net/espokemon/images/9/95/Pok%C3%A9mon_Amarillo.png"
        ),
        GameBo(
            5,
            "Metal gear solid",
            "Konami",
            1998,
            listOf(CompatiblePlatform.PLAYSTATION),
            Pegi.PEGI18,
            "https://media.vandal.net/m/181/metal-gear-solid-201961220192328_1.jpg"
        ),
        GameBo(
            6,
            "Halo",
            "Bungie",
            2001,
            listOf(CompatiblePlatform.XBOX),
            Pegi.PEGI18,
            "https://www.liveabout.com/thmb/F4q6V-rm8hOR_g7nOQC4_kDjog0=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/halo-combat-evolved-game-57900ff03df78c09e9a2071e.jpg"
        ),
        GameBo(
            0,
            "GTA V",
            "RockStar",
            2013,
            listOf(CompatiblePlatform.PLAYSTATION, CompatiblePlatform.XBOX),
            Pegi.PEGI18,
            "https://image.api.playstation.com/cdn/EP1004/CUSA00411_00/eXsWlP0EkcVkLPHgU4pjflmg07252yU8.png"
        ),
        GameBo(
            1,
            "Call of duty 4",
            "Activision",
            2007,
            listOf(CompatiblePlatform.PLAYSTATION, CompatiblePlatform.XBOX),
            Pegi.PEGI18,
            "https://static.wikia.nocookie.net/cod_esports_gamepedia_en/images/a/a5/Call_of_Duty_4_Game_Cover.jpg"
        ),
        GameBo(
            4,
            "Crash Team Racing",
            "Nintendo",
            2019,
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
