using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour {

    public float spedd;
    private Rigidbody rb;
    

	// Use this for initialization
	void Start () {
        rb = GetComponent<Rigidbody>();
	}

     void FixedUpdate()
    {
        float horizontal = Input.GetAxis("Horizontal");
        float vertical = Input.GetAxis("Vertical");

        Vector3 move = new Vector3(horizontal, 0.0f, vertical);
        rb.AddForce(move * spedd);
    }

    // Update is called once per frame
    void Update () {
       


    }
}
