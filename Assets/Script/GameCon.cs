using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public enum GameState { Idle, playing, Ended };



public class GameCon : MonoBehaviour {

    public float parallaxSpeed = 0.02f;
    public RawImage back;
    public RawImage platf;
    public GameObject uiIdle;
    public GameObject uiscore;

    public Text pointsText;
    public Text recordText;

    public GameState gameState = GameState.Idle;

    public GameObject player;
    public GameObject enemyGenerator;

    private AudioSource musicPlayer;

    public float scaleTime = 6f;
    public float scaleInc = .25f;

    private int points = 0;


	// Use this for initialization
	void Start () {
        musicPlayer = GetComponent<AudioSource>();
        recordText.text = "Puntuacion Maxima: " + GetMaxScore().ToString();

    }
	
	// Update is called once per frame
	void Update () {

        //comienza el juego
        if (gameState == GameState.Idle && (Input.GetKeyDown("up") || Input.GetMouseButtonDown(0)))
        {
            gameState = GameState.playing;
            uiIdle.SetActive(false);

            uiscore.SetActive(true);

            player.SendMessage("UpdateState", "playerRun");
            enemyGenerator.SendMessage("StartGenerator");
            musicPlayer.Play();
            InvokeRepeating("GameTimeScale", scaleTime, scaleTime);
        } 

        //juego en marcha
        else if (gameState == GameState.playing)
        {
            parallax();

        }
        else if (gameState == GameState.Ended)
        {
            if(Input.GetKeyDown("up") || Input.GetMouseButtonDown(0)){
                RestartGame();
            }

            }
    }

    void parallax()
    {
        float finalSpeed = parallaxSpeed * Time.deltaTime;
        back.uvRect = new Rect(back.uvRect.x + finalSpeed, 0f, 1f, 1f);
        platf.uvRect = new Rect(platf.uvRect.x + finalSpeed * 4, 0f, 1f, 1f);
    }

    public void RestartGame()
    {
        SceneManager.LoadScene("SampleScene");
    }

    void GameTimeScale()
    {
        Time.timeScale += scaleInc;
    }

    public void ResetTimeScale()
    {
        CancelInvoke("GameTimeScale");
        Time.timeScale = 1f;
            
    }

    public void IncreasePoints()
    {
        points++;
        pointsText.text = points.ToString();
        if(points >= GetMaxScore())
        {
            recordText.text = "Puntuacion Maxima: " + points.ToString();
            SaveScore(points);
        }
    }

    public int GetMaxScore()
    {
        return PlayerPrefs.GetInt("Max Points", 0);

    }
    public void SaveScore(int currentPoints)
    {
        PlayerPrefs.SetInt("Max Points", currentPoints);
    }
}
