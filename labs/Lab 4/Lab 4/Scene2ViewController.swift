//
//  Scene2ViewController.swift
//  Lab 4
//
//  Created by Serena Buxton on 10/8/18.
//  Copyright © 2018 Serena Buxton. All rights reserved.
//

import UIKit

class Scene2ViewController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var userReason: UITextField!
    @IBOutlet weak var userWorry: UITextField!
    override func viewDidLoad() {
        userWorry.delegate=self
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }

    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
        if segue.identifier == "doneWorry"{
            let scene1ViewController = segue.destination as! ViewController
            //check to see that text was entered in the textdields
            if userWorry.text!.isEmpty == false{
                scene1ViewController.user.currentWorry=userWorry.text
            }
            if userReason.text!.isEmpty == false{
                scene1ViewController.user.currentReason=userReason.text
            }
        }
    }
 

}
