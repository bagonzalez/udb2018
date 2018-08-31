using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;


public class GameCon : MonoBehaviour {

    public float parallaxSpeed = 0.02f;
    public RawImage back;
    public RawImage platf;
    public GameObject uiIdle;

    public enum GameState {Idle, playing};
    public GameState gameState = GameState.Idle;


	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {

        //comienza el juego
        if (gameState == GameState.Idle && (Input.GetKeyDown("up") || Input.GetMouseButtonDown(0)))
        {
            gameState = GameState.playing;
            uiIdle.SetActive(false);
        } 

        //juego en marcha
        else if (gameState == GameState.playing)
        {
            parallax();

        }
    }

    void parallax()
    {
        float finalSpeed = parallaxSpeed * Time.deltaTime;
        back.uvRect = new Rect(back.uvRect.x + finalSpeed, 0f, 1f, 1f);
        platf.uvRect = new Rect(platf.uvRect.x + finalSpeed * 4, 0f, 1f, 1f);
    }
}
