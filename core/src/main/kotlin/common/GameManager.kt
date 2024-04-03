package common

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.math.Vector2
import planes.Planes

object GameManager {

    private  var packages : Int = 0
    private  var lifes : Int = 3
    private var birdSpeed : Float = 100f
    private var opponentPlaneSpeed : Float = 100f
    private var packageSpeed : Float = 100f
    private var birdFrequency : Float = 1f
    private var skyScraperFrequency : Float = 1f
    private var opponentPlaneFrequency : Float = 3f
    private var packageFrequency : Float = 2f
    private var result : Int = 0
    private var HIGHSCORE : String = "HIGHSCORE"
    private var NAME : String = "NAME"
    private var backgroundScrollSpeed : Vector2 = Vector2(0.05f, 0f)
    private  var preferences : Preferences = Gdx.app.getPreferences(Planes::class.simpleName)


     fun getBackgroundScrollSpeed(): Vector2 {
        return backgroundScrollSpeed
    }

    fun setName(text: String) {
        preferences.putString(NAME, text)
        preferences.flush()
    }

    fun getName() : String{
        return preferences.getString(NAME,"")
    }

    fun setHighscore(result : Int) {
        preferences.putInteger(HIGHSCORE, result)
        preferences.flush()
    }

    fun getHighscore(): Int {
        return preferences.getInteger(HIGHSCORE, 0)
    }
    fun getBirdSpeed() : Float {
        return birdSpeed
    }

    fun getPackageSpeed() : Float {
        return packageSpeed
    }

    fun getOpponentPlaneSpeed() : Float {
        return opponentPlaneSpeed
    }

    fun setDifficulty(difficulty : String){

        when{(difficulty == "easy") -> {
            birdFrequency = 1f
            packageFrequency = 3f
            opponentPlaneFrequency = 3f
            lifes = 3
            birdSpeed = 100f
            packageSpeed = 100f
            opponentPlaneSpeed = 100f
            skyScraperFrequency = 3f

        }}

        when{(difficulty == "medium") -> {
            birdFrequency = 1f
            packageFrequency = 2f
            opponentPlaneFrequency = 1.5f
            lifes = 3
            birdSpeed = 150f
            opponentPlaneSpeed = 150f
            packageSpeed = 150f
            skyScraperFrequency = 2f
            backgroundScrollSpeed =  Vector2(0.1f, 0f)
        }}

        when{(difficulty == "hard") -> {
            birdFrequency = 0.5f
            packageFrequency = 1f
            opponentPlaneFrequency = 1f
            lifes = 3
            birdSpeed = 200f
            opponentPlaneSpeed = 200f
            packageSpeed = 200f
            skyScraperFrequency = 1.5f
            backgroundScrollSpeed =  Vector2(0.2f, 0f)
        }}
    }

    fun getSkyScraperFrequency() : Float {
        return skyScraperFrequency
    }

    fun getBirdFrequency() : Float {
        return birdFrequency
    }

    fun getOpponentPlaneFrequency() : Float {
        return opponentPlaneFrequency
    }

    fun getPackageFrequency() : Float {
        return packageFrequency
    }

    fun getLives() : Int {
        return lifes
    }

    fun getPackages() : Int {
        return packages
    }

    fun resetResult() {
        packages = 0
        lifes = 3
    }


    fun isGameOver() : Boolean {
        if(lifes <= 0) {
            if(packages > getHighscore()) {
                setHighscore(packages)
            }
            return true
        }
            return false
    }
    fun pickPackage() {
        packages++
        result++
    }

    fun damage() {
        lifes--
    }

    fun damageBig() {
        lifes = 0
    }

}
