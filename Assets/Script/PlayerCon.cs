using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerCon : MonoBehaviour {

    private Animator animator;
    public GameObject game;
    public GameObject enemyGen;
    public AudioClip Dieclip;
    private AudioSource audioPlayer;
	// Use this for initialization
	void Start () {
        animator = GetComponent<Animator>();
        audioPlayer = GetComponent<AudioSource>();
		
	}
	
	// Update is called once per frame
	void Update () {
        bool gamePlaying = game.GetComponent<GameCon>().gameState == GameState.playing;
        if (gamePlaying && (Input.GetKeyDown("up") || Input.GetMouseButtonDown(0))){
            UpdateState("playerJump");
        }
		
	}

    public void UpdateState(string state = null )
    {
        if(state != null)
        {
            animator.Play(state);
        }

    }

    void OnTriggerEnter2D(Collider2D other)
    {
        if (other.gameObject.tag == "Enemy")
        {
            UpdateState("playerDie");
            game.GetComponent<GameCon>().gameState = GameState.Ended;
            enemyGen.SendMessage("CancelGenerator", true);

            game.SendMessage("ResetTimeScale");

            game.GetComponent<AudioSource>().Stop();
            audioPlayer.clip = Dieclip;
            audioPlayer.Play();
        }
        else if (other.gameObject.tag == "point")
        {
            game.SendMessage("IncreasePoints");
        }
    }
    void GameReady()
    {
        game.GetComponent<GameCon>().gameState = GameState.Ended;

    }

}
