//
//  ViewController.swift
//  Workout
//
//  Created by Serena Buxton on 10/18/18.
//  Copyright © 2018 Serena Buxton. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate{

    @IBOutlet weak var workoutImage: UIImageView!
    @IBOutlet weak var workoutTimeTextField: UITextField!
    @IBOutlet weak var weeklySwitch: UISwitch!
    @IBOutlet weak var workoutsWeekSlider: UISlider!
    @IBOutlet weak var workoutWeekLabel: UILabel!
    @IBOutlet weak var typeSegment: UISegmentedControl!
    @IBOutlet weak var workoutButton: UIButton!
    @IBOutlet weak var milesLabel: UILabel!
    @IBOutlet weak var caloriesLabel: UILabel!
    
    @IBOutlet weak var emailLabel: UILabel!
    @IBOutlet weak var nameLabel: UILabel!
    var workoutPerWeek : Float = 5
    var totTime : Float = 0
    
    var user=info()
    
    @IBAction func setWorkoutsPerWeek(_ sender: UISlider) {
        workoutPerWeek = sender.value
        workoutWeekLabel.text = String(workoutPerWeek)
    }
    
    @IBAction func changeImage(_ sender: UISegmentedControl) {
        if typeSegment.selectedSegmentIndex == 0{
            workoutImage.image = UIImage(named: "run.png")
        }
        else if typeSegment.selectedSegmentIndex == 1{
            workoutImage.image = UIImage(named: "swim.png")
        }
        else{
            workoutImage.image = UIImage(named: "bike.png")
        }
    }
    
    @IBAction func callCalculation(_ sender: UIButton) {
        calculation()
    }
    
    @IBAction func unwindSegue (_ segue:UIStoryboardSegue){
        nameLabel.text=user.name
        emailLabel.text=user.email
        return
    }
    
    func calculation(){
        if !workoutTimeTextField.text!.isEmpty {
            let minutes = Float (workoutTimeTextField.text!)
            if weeklySwitch.isOn{
                workoutPerWeek = workoutsWeekSlider.value
                totTime = minutes! * workoutPerWeek
            }
            else{
                totTime = minutes!
            }
            var miles: Float
            var calories: Float
            if typeSegment.selectedSegmentIndex == 0{
                miles = totTime/60 * 6
                calories = totTime/60 * 600
            }
            else if typeSegment.selectedSegmentIndex == 1{
                miles = totTime/60 * 2
                calories = totTime/60 * 420
            }
            else{
                miles = totTime/60 * 15
                calories = totTime/60 * 510
            }
            milesLabel.text = String (miles)
            caloriesLabel.text = String (calories)
        }
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        if !workoutTimeTextField.text!.isEmpty{
            let minutes = Float (workoutTimeTextField.text!)
            if minutes! < Float(30.0) {
                //create a UIAlertController object
                let alert=UIAlertController(title: "Warning", message: "You really should workout more!", preferredStyle: UIAlertControllerStyle.alert)
                //create a UIAlertAction object for the button
                let cancelAction=UIAlertAction(title: "Cancel", style:UIAlertActionStyle.cancel, handler: nil)
                alert.addAction(cancelAction) //adds the alert action to the alert object
                let okAction=UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {action in
                    self.workoutTimeTextField.text = "30"
                    self.calculation()
                })
                alert.addAction(okAction)
                present(alert, animated: true, completion: nil)
            }
        }
        return
    }
    
    override func viewDidLoad() {
        workoutTimeTextField.delegate = self
        milesLabel.text = "0.0"
        caloriesLabel.text = "0.0"
        workoutWeekLabel.text = "5.0"
        nameLabel.text=""
        emailLabel.text=""
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

