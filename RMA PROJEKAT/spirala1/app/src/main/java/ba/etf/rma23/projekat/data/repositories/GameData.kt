package ba.etf.rma23.projekat.data.repositories

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Game(
    //potrebno je klasu prilagoditi da bude serializible i da je konverter može pretvoriti u JSON i obratno
    //bilo je potrebno napraviti odredene data klase, buduci da u postamanu u JSON fajlu vrati za igricu listu

    @SerializedName("name") val title: String,
    @SerializedName("platform") val platform: List<Platform> = listOf(),
    @SerializedName("first_release_date") val releaseDate: String,
    @SerializedName("rating") val rating: Double,
    val coverImage: String,
    val esrbRating: String,
    val developer: String,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("geners") val genre: String,
    val description: String,
    //val userImpressions: List<UserImpression>,
): Serializable


data class Platform(val id: Int, val name: String)


abstract class UserImpression {
    abstract val username : String
    abstract val timestamp : Long
}

//val age=list<age>=listof()

data class UserRating(
    override val username: String,
    override val timestamp: Long,
    val rating: Double
): UserImpression()

data class UserReview(
    override val username: String,
    override val timestamp: Long,
    val review: String
): UserImpression()


    class GameData {}
        /*
        companion object {
            private val games = listOf(

                Game(
                    "PUBG: Battlegrounds", " Stadia streaming platform", "December 20, 2017",
                    4.3, "PUBG: Battlegrounds", "9.7",
                    " PUBG Studios", "Krafton / Tencent", "battle royale genre",
                    "PUBG: Battlegrounds (previously known as PlayerUnknown's Battlegrounds) is a battle royale game developed by PUBG Studios and published by Krafton. " +
                            "The game, which was inspired by the Japanese film Battle Royale (2000), is based on previous mods created by Brendan",
                    listOf(
                        UserRating("John", 112022, 9.3),
                        UserRating("Smith", 122021, 9.7),
                        UserRating("Alex", 12018, 7.1),
                        UserRating("Bob", 102017, 10.0),
                        UserRating("Emma", 62018, 9.9)
                    )
                ),
                Game(
                    "Crossfire", "Microsoft Windows", "May 3, 2007", 2.9,
                    "Crossfire",
                    "5.7", "Smilegate Entertainment", "Smilegate", "First-person shooter",
                    "Crossfire is an online tactical first-person shooter game developed by Smilegate Entertainment for Microsoft Windows. It was first released in South Korea on May 3, 2007.",
                    listOf(
                        UserRating("Doe", 72019, 4.5),
                        UserRating("Jane", 82022, 4.0),
                        UserRating("Alice", 102021, 4.7),
                        UserRating("Bob", 62008, 4.0),
                        UserRating("Charlie", 52010, 3.8)
                    )
                ),
                Game(
                    "Minecraft", "Multi-platform", "November 18, 2011", 5.0,
                    "https://en.wikipedia.org/wiki/File:Minecraft_cover.png", "9.9", "Mojang Studios",
                    "Xbox Game Studios", "survival", "Minecraft is a sandbox game developed by Mojang Studios. The game was created by Markus \"Notch\" Persson in the Java programming language. ",
                    listOf(
                        UserRating("User1", 122011, 9.9),
                        UserRating("User2", 12012, 10.0),
                        UserRating("User3", 22012, 8.8),
                        UserRating("User4", 112012, 9.8),
                        UserRating("User5", 122021, 9.9)
                    )
                ),
                Game(
                    "Pac-Man", "Multi-platform", "May 22, 1980", 4.3,
                    "https://upload.wikimedia.org/wikipedia/en/1/16/Pac_flyer.png", "6.1", "Namco",
                    "Namco", "maze", "Pac-Man,[a] originally called Puck Man in Japan, " +
                            "is a 1980 maze action video game developed and released by Namco for arcades.",
                    listOf(
                        UserRating("John", 111981, 4.5),
                        UserRating("Smithh", 122000, 7.7),
                        UserRating("Alice", 72002, 9.0),
                        UserRating("Ema", 51999, 4.0),
                        UserRating("User", 182002, 3.6)
                    )
                ),
                Game(
                    "FIFA 18", "Multi-platform", "September 29, 2017", 4.2,
                    "https://upload.wikimedia.org/wikipedia/en/d/d4/FIFA18cover.png", "8.1", "Jamie Sutcliffe",
                    "EA Sports", "Sports", "FIFA 18 is a football simulation video game developed and published by " +
                            "Electronic Arts and released worldwide on 29 September 2017 for Microsoft Windows",
                    listOf(
                        UserRating("Mia", 122017, 7.8),
                        UserRating("Josh", 112017, 8.1),
                        UserRating("Mia", 72018, 9.0),
                        UserRating("Louis", 12018, 8.0),
                        UserRating("User2", 122018, 8.6)
                    )
                ),
                Game(
                    "Candy Crush Saga", "Multi-platform", "April 12, 2012", 4.6,
                    "https://upload.wikimedia.org/wikipedia/en/thumb/b/bc/Candy_Crush_logo.png/220px-Candy_Crush_logo.png", "8.1",
                    "King", "King", "Puzzle", "Candy Crush Saga is considered one of the first and most successful uses of a freemium model; ",
                    listOf(
                        UserReview("Emma", 12013, "I have been playing for a while and I wish some of the levels were made easier, especially the nightmare difficulty. It seems they are made impossible unless you spend money on boosters or extra moves. I don't feel that is right especially when its one after another back to back, and I did pay to pass the previous level.... " +
                                "Getting to the point when I will just delete the game and forget about it...")
                    )
                ),
                Game(
                    "Mini World", "Multi-platform", "December 26, 2015", 4.1,
                    "data:image/jpeg;base64,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",
                    "6.0", "Miniwan", "Miniwan", "Puzzle", "Mini World is the ultimate dream game for you to let your " +
                            "creativity fly! Create and build anything you can imagine, a house, apartment, castle or even a city of your own",
                    listOf(
                        UserReview("zyuilia", 122016, "Once again I play stupid game")
                    )
                ),
                Game(
                    "Fortnite", "Multi-platform", "September 26, 2017", 3.5,
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/FortniteLogo.svg/1920px-FortniteLogo.svg.png",
                    "9.6", "Epic Games", "Epic Games", " royale genre", "Fortnite is an online video game developed by Epic Games and released in 2017. It is available in three distinct game mode versions that otherwise " +
                            "share the same general gameplay and game engine",
                    listOf(
                        UserReview("Acalodo ", 102017, "This Game is Not that violent and non relistic. The game is fun and should be played by every one.")
                    )
                ),
                Game(
                    "Marvel's Avengers", "PlayStation 4", "Mar 18, 2021", 4.6,
                    "https://m.media-amazon.com/images/I/71CVPn65SXL._SX425_.jpg", "Teen", "SEGA", "Square Enix Europe",
                    "Action-adventure", "Marvel's Avengers is a 2020 action-adventure video game developed by Crystal Dynamics and published by Square Enix Europe.",
                    listOf(
                        UserReview("Tim Eggert", 82022, "From the headline when I say that, well it doesn't mean I didn't like it. I like the game but I feel like Square Enix ruined it through microtransactions and they're still putting it in. " +
                                "Not just that, the game is still a bit broken. ")
                    )
                ),
                Game(
                    "Spider-Man: Miles Morales", "Multi-platform", "18 Nov, 2022", 4.5,
                    "https://m.media-amazon.com/images/I/71wi94SiJHL._AC_SX679_.jpg", "Teen", "Insomniac Games, Nixxes Software",
                    "PlayStation PC LLC", "Action-adventure", "After the events of Marvel’s Spider-Man Remastered, teenage Miles Morales is adjusting to his new home while following in " +
                            "the footsteps of his mentor, Peter Parker, as a new Spider-Man. ",
                    listOf(
                        UserRating("IT-S Khan", 122022, 4.9)
                    )
                )
                )

            fun getAll(): List<Game> {  //metoda getAll
                return games
            }

            fun getDetails(title: String): Game? {  //metoda getDetails
                return games.find { it.title == title }

            }
        }

    }



     */

