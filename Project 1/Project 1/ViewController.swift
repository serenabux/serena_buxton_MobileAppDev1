//
//  ViewController.swift
//  Project 1
//
//  Created by Serena Buxton on 10/10/18.
//  Copyright © 2018 Serena Buxton. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    
    @IBOutlet weak var subsNumLabel: UILabel!
    @IBOutlet weak var pos1Txt: UITextField!
    @IBOutlet weak var pos2Txt: UITextField!
    @IBOutlet weak var pos3Txt: UITextField!
    @IBOutlet weak var pos4Txt: UITextField!
    @IBOutlet weak var pos5Txt: UITextField!
    @IBOutlet weak var pos6Txt: UITextField!
    @IBOutlet weak var liberoLText: UITextField!
    @IBOutlet weak var liberoRText: UITextField!
    @IBOutlet weak var teamSegment: UISegmentedControl!
    
    
    var team1 = [subTracker] ()
    var team2 = [subTracker] ()
    
    var subs : Int = 0
    
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    
    
    //Returns 1 if a successful sub, 2 too many subs, 3 if the player is already in the current position, 2+position already played in if the player has already played in another position
    func update(_ element: Int,_ player: Int, _ team: [subTracker])->Int{
        //check to make sure sub is valid
        //check 1 => player is not already in that position
        if team[element].currentPlayer==player{
            //no need to continue so return
            return 3
        }
        //check 2=> make sure that the player has not been in any of the positions for their team
        for i in 0...6{
            let sizeSubs = team[i].subs.count
            for j in 0...sizeSubs{
                if(j==element){
                    //position player is trying to sub into so this is okay
                    break
                }
                else if(team[i].subs[j]==player){
                    return j+4
                }
            }
        }
        
        team[element].currentPlayer=player
        team[element].subs.append(player)
        subs = subs-1
        subsNumLabel.text = String(subs)
        //allPos[element].currentPlayer=player
        
        //if !textField.text!.isEmpty{
        
        
        /*if(textField==pos1Txt){
         Pos1Team1.currentPlayer = Int(textField.text!)!
         Pos1Team1.subs.append(Int(textField.text!)!)
         print(Pos1Team1.currentPlayer!)
         print(Pos1Team1.currentPlayer!)
         }*/
        //}
        return 1
    }
    func textFieldDidEndEditing(_ textField: UITextField) {
        //retValue is the return value when update is called, element is the element corresponding to the text box for the team array, and t is the team
        var retValue: Int
        var element: Int
        var t: [subTracker]
        if !textField.text!.isEmpty{
            if teamSegment.selectedSegmentIndex==0{
                t = team1
            }
            else{
                t = team2
            }
            switch textField{
            case pos1Txt: element = 0
            case pos2Txt: element = 1
                
                //TODO: finish all cases
            //TODO: look at how to throw an error if this occurs
            default: element = 0
            }
            retValue = update(element, Int(textField.text!)!,t)
            print(retValue)
            if retValue==2{
                //Warn user player already in position
                //create a UIAlertController object
                let alert=UIAlertController(title: "Warning", message: "Player is already in this position. Number of subs has not been changed", preferredStyle: UIAlertControllerStyle.alert)
                //create UI alert action to the alert object
                let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil)
                //add alert to alert object
                alert.addAction(okAction)
                //present alert
                present(alert, animated: true, completion: nil)
            }
            else if retValue==3{
                //Warn user player already in position
                //create a UIAlertController object
                let alert=UIAlertController(title: "Warning", message: "Player is already in this position. Number of subs has not been changed", preferredStyle: UIAlertControllerStyle.alert)
                //create UI alert action to the alert object
                let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: nil)
                //add alert to alert object
                alert.addAction(okAction)
                //present alert
                present(alert, animated: true, completion: nil)
            }
            else if retValue>3{
                //Throw error that player has already played in position
                //create a UIAlertController object
                let alert=UIAlertController(title: "Illegal Sub", message: "Player has already played in" + String(retValue-4) + " position. Alert 2nd Ref.", preferredStyle: UIAlertControllerStyle.alert)
                //create UI alert action to the alert object
                let okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {action in textField.text=String(t[element].currentPlayer!)})
                //add alert to alert object
                alert.addAction(okAction)
                //present alert
                present(alert, animated: true, completion: nil)
                //no need to continue so return
                
            }
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        pos1Txt.delegate=self
        pos2Txt.delegate=self
        pos3Txt.delegate=self
        pos4Txt.delegate=self
        pos5Txt.delegate=self
        pos6Txt.delegate=self
        //declare an empty instance of the class
        let tmp = subTracker()
        //append tmp into the array for 6 times for each team
        for i in 0...6{
            team1.append(tmp)
            team2.append(tmp)
            
        }
        subsNumLabel.text=String(subs)
        
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
}
