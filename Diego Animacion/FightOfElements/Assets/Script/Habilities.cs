using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Habilities : MonoBehaviour {

	public GameObject Me;
	public GameObject BaseRocks;
	public GameObject WallRocks;
	public GameObject PositionWallRocks;
	public bool CDBaseRock;
	public bool CDWallRock;
	public float TimeCDBaseRock;
	public float TimeCDWallRock;
	private Vector3 _positionBaseRock;
	private Vector3 _positionWallRock;
	// Use this for initialization
	void Start () {
		_positionBaseRock = new Vector3();
		CDBaseRock = true;
	}
	
	// Update is called once per frame
	void Update () {
		_positionBaseRock = Me.transform.position;
		_positionBaseRock.y -= 10;

		_positionWallRock = PositionWallRocks.transform.position;
		_positionWallRock.y -= 0.5f;

		TimerComparation();

		if (Input.GetKey(KeyCode.LeftControl))
		{
			if (CDBaseRock)
			{
				Instantiate(BaseRocks, _positionBaseRock, Quaternion.identity);
				//TimeCDBaseRock = Time.time + 10;
				TimeCDBaseRock = Time.time + 1;
				CDBaseRock = false;
			}
		}

		if (Input.GetKey(KeyCode.E))
		{
			if (CDWallRock)
			{
				Instantiate(WallRocks, _positionWallRock, PositionWallRocks.transform.rotation);
				//TimeCDBaseRock = Time.time + 10;
				TimeCDWallRock = Time.time + 1;
				CDWallRock = false;
			}
		}
	}

	private void TimerComparation()
	{
		float timeNow = Time.time;
		Debug.Log("Time" + timeNow);
		if (timeNow >= TimeCDBaseRock)
		{
			CDBaseRock = true;
		}

		if (timeNow >= TimeCDWallRock)
		{
			CDWallRock = true;
		}
		
	}
}
