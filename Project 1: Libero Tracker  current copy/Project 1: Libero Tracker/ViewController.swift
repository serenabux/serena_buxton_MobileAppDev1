//
//  ViewController.swift
//  Project 1: Libero Tracker
//
//  Created by Serena Buxton on 10/13/18.
//  Copyright © 2018 Serena Buxton. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var Pos1Text: UITextField!
    @IBOutlet weak var Pos2Text: UITextField!
    @IBOutlet weak var Pos3Text: UITextField!
    @IBOutlet weak var Pos4Text: UITextField!
    @IBOutlet weak var Pos5Text: UITextField!
    @IBOutlet weak var Pos6Text: UITextField!
    @IBOutlet weak var liberoText: UITextField!
    @IBOutlet weak var rLiberoText: UITextField!
    @IBOutlet weak var teamSegmentControl: UISegmentedControl!
    @IBOutlet weak var subNumLabel: UILabel!
    
    var Team1L: Int!
    var Team1R: Int!
    var Team2L: Int!
    var Team2R: Int!
    
    var subs: [Int] = [0,0]
    
    //The libero for each team is allowed to be in two positions that are three positions apart. Since at the start the libero has not been in any position they are all equal to -1
    var liberoPositions: [Int] = [-1,-1,-1,-1]
    
    
  //declare an array to hold the array of subs for each position
  //elements 0-5 are for team 1, elements 6-11 are for team 2
    
    var allPos: [[Int]?] = [[],[],[],[],[],[],[],[],[],[],[],[]]
    
    
    @IBAction func updateTeam(_ sender: UISegmentedControl) {
        var player: Int = -1
        if teamSegmentControl.selectedSegmentIndex == 0 {
            //referenced apples developer site
            self.view.backgroundColor = #colorLiteral(red: 0.6745098039, green: 0.8509803922, blue: 0.968627451, alpha: 1)
            if (allPos[0]?.last) != nil {
                player = (allPos[0]?.last)!
                self.Pos1Text.text = String (player)
            }
            else{
                self.Pos1Text.text = ""
            }
            if (allPos[1]?.last) != nil {
                player = (allPos[1]?.last)!
                self.Pos2Text.text = String (player)
            }
            else{
                self.Pos2Text.text = ""
            }
            if (allPos[2]?.last) != nil {
                player = (allPos[2]?.last)!
                self.Pos3Text.text = String (player)
            }
            else{
                self.Pos3Text.text = ""
            }
            if (allPos[3]?.last) != nil {
                player = (allPos[3]?.last)!
                self.Pos4Text.text = String (player)
            }
            else{
                self.Pos4Text.text = ""
            }
            if (allPos[4]?.last) != nil {
                player = (allPos[4]?.last)!
                self.Pos5Text.text = String (player)
            }
            else{
                self.Pos5Text.text = ""
            }
            if (allPos[5]?.last) != nil {
                player = (allPos[5]?.last)!
                self.Pos6Text.text = String (player)
            }
            else{
                self.Pos6Text.text = ""
            }
            if Team1L != nil {
                self.liberoText.text = String (Team1L)
            }
            else{
                self.liberoText.text = ""
            }
            if Team1R != nil {
                self.rLiberoText.text = String (Team1R)
            }
            else{
                self.rLiberoText.text = ""
            }
            self.subNumLabel.text = String(subs[0])
            

        }
        else{
           self.view.backgroundColor = #colorLiteral(red: 1, green: 0.6, blue: 0.6, alpha: 1)
            if (allPos[6]?.last) != nil {
                player = (allPos[6]?.last)!
                self.Pos1Text.text = String (player)
            }
            else{
                self.Pos1Text.text = ""
            }
            if (allPos[7]?.last) != nil {
                player = (allPos[7]?.last)!
                self.Pos2Text.text = String (player)
            }
            else{
                self.Pos2Text.text = ""
            }
            if (allPos[8]?.last) != nil {
                player = (allPos[8]?.last)!
                self.Pos3Text.text = String (player)
            }
            else{
                self.Pos3Text.text = ""
            }
            if (allPos[9]?.last) != nil {
                player = (allPos[9]?.last)!
                self.Pos4Text.text = String (player)
            }
            else{
                self.Pos4Text.text = ""
            }
            if (allPos[10]?.last) != nil {
                player = (allPos[10]?.last)!
                self.Pos5Text.text = String (player)
            }
            else{
                self.Pos5Text.text = ""
            }
            if (allPos[11]?.last) != nil {
                player = (allPos[11]?.last)!
                self.Pos6Text.text = String (player)
            }
            else{
                self.Pos6Text.text = ""
            }
            if Team2L != nil {
                self.liberoText.text = String (Team2L)
            }
            else{
                self.liberoText.text = ""
            }
            if Team2R != nil {
                self.rLiberoText.text = String(Team2R)
            }
            else{
                self.rLiberoText.text = ""
            }
            self.subNumLabel.text = String(subs[1])
        }
    }
    
    func setLiberos(_ position: String, _ team: Int, _ player: Int){
        if team == 1{
            if position == "L"{
                self.Team1L = player
            }
            else {
                self.Team1R = player
            }
        }
        else{
            if position == "L"{
                self.Team2L = player
            }
            else {
                self.Team2R = player
            }
        }
    }
    
    func updateLibero(_ position: Int, _ team: Int, _ player: Int) -> Bool{
        let startIndex : Int
        let currentL: Int
        let currentR: Int
        var length: Int
        var i: Int
        if team == 1{
            startIndex = 0
            if Team1L != nil{
                currentL = Team1L
            }
            else{
                currentL = -1
            }
            if Team1R != nil{
                currentR = Team1R
            }
            else{
                currentR = -1
            }
        }
        else{
            startIndex=2
            if Team2L != nil{
                currentL = Team2L
            }
            else{
                currentL = -1
            }
            if Team2R != nil{
                currentR = Team2R
            }
            else{
                currentR = -1
            }
        }
        if liberoPositions[startIndex] == -1{
            liberoPositions[startIndex] = position
            liberoPositions[startIndex+1] = (position+3)%6
            allPos[position]?.append(player)
            return true
        }
        else{
            if position==liberoPositions[startIndex] || position==liberoPositions[startIndex+1]{
                for _ in 0...1{
                    i=liberoPositions[startIndex]
                    length = (allPos[i]?.count)!
                    if length>0{
                        if allPos[i]![length-1] == currentL   {
                            if player == currentL{
                                //Ignore since libero already in position
                                return true
                            }
                            else{
                                // Otherwise R is going in for L
                                allPos[position]?.append(player)
                                return true
                            }
                        }
                        if allPos[i]![length-1] == currentR {
                            if player == currentR{
                                //Ignore since libero already in position
                                return true
                            }
                            else{
                                // Otherwise L is going in for R
                                allPos[position]?.append(player)
                                return true
                            }
                        }
                    }
                }
                allPos[position]?.append(player)
                return true
            }
            else{
                return false
            }
        }
    }
    
    //This function updates subs for non-libero transactions
    //return 1 when successful and 2 when illegal sub, 3 when illegal sub with libero
    func updateSubs(_ position: Int, _ player: Int, _ team: Int) -> Int{
        let libero: Int
        let rLibero: Int
        if team==1{
            if Team1L != nil {
                libero = Team1L
            }
            else{
                //set to -1 because there will never be a player that has a negative number
                libero = -1
            }
            if Team1R != nil {
                rLibero = Team1R
            }
            else{
                //set to -1 because there will never be a player that has a negative number
                rLibero = -1
            }
            
        }
        else{
            if Team2L != nil {
                libero = Team2L
            }
            else{
                //set to -1 because there will never be a player that has a negative number
                libero = -1
            }
            if Team2R != nil {
                rLibero = Team2R
            }
            else{
                //set to -1 because there will never be a player that has a negative number
                rLibero = -1
            }
        }
        var firstIndex: Int = 0
        var lastIndex: Int = 5
        if team==2{
            firstIndex = 6
            lastIndex = 11
        }
        var length: Int = 0
        //if it is the first player for that position then you do not want to update subs
        var firstPlayer: Bool = false
        //first check to see if player has already been in the position they are subbing to, if they have it has already been checked to see if it is safe to sub
        //last element is a current player so if this is the same do not update subs
        length = allPos[position]!.count
        if length>0{
            length = allPos[position]!.count
            if allPos[position]![length-1]==player{
                return 1
            }
            else if allPos[position]![length-1] == libero || allPos[position]![length-1] == rLibero{
                if allPos[position]![length-2] != player{
                    //TODO make it throw the specific error
                    return 3
                }
                else{
                    allPos[position]!.append(player)
                    return 1
                }
            }
            for i in 0...(length-1){
                if allPos[position]![i]==player{
                    allPos[position]!.append(player)
                    subs[team-1]=subs[team-1]+1
                    return 1
                }
            }
        }
        else{
            firstPlayer = true
        }
        for i in firstIndex...lastIndex{
            length = allPos[i]!.count
            if length > 0{
                if i != position{
                    for j in 0...(length-1){
                        if allPos[i]![j]==player{
                            //throw error
                            return 2
                        }
                    }
                }
            }
        }
            //the sub is valid at thia point add to the desired position
        allPos[position]!.append(player)
        if !firstPlayer{
            subs[team-1] = subs[team-1] + 1
        }
        return 1
    }
    
    
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    
    func throwErrorPopup(_ textField: UITextField, _ errorCode: Int, _ position: Int, _ team: Int){
        switch errorCode {
        case 1:
            //create a UIAlertController object
            let alert=UIAlertController(title: "No Libero", message: "There is no libero declared. Alert 2nd Ref.", preferredStyle: UIAlertControllerStyle.alert)
            //create UI alert action to the alert object
            var okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {action in textField.text=""})
            let length = (allPos[position]?.count)!
            if length > 0 {
                okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {action in textField.text=String(self.allPos[position]![length-1])})
            }
            //add alert to alert object
            alert.addAction(okAction)
            //present alert
            present(alert, animated: true, completion: nil)
        case 2:
            //create a UIAlertController object
            let alert=UIAlertController(title: "No Second Libero", message: "There is no second libero declared. Alert 2nd Ref.", preferredStyle: UIAlertControllerStyle.alert)
            //create UI alert action to the alert object
            var okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {action in textField.text=""})
            let length = (allPos[position]?.count)!
            if length > 0 {
                okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {action in textField.text=String(self.allPos[position]![length-1])})
            }
            //add alert to alert object
            alert.addAction(okAction)
            //present alert
            present(alert, animated: true, completion: nil)
        case 3:
            //create a UIAlertController object
            let alert=UIAlertController(title: "Illegal Sub", message: "Player has already played in another position. Alert 2nd Ref.", preferredStyle: UIAlertControllerStyle.alert)
            //create UI alert action to the alert object
            var okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {action in textField.text=""})
            let length = (allPos[position]?.count)!
            if length > 0 {
                okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {action in textField.text=String(self.allPos[position]![length-1])})
            }
            //add alert to alert object
            alert.addAction(okAction)
            //present alert
            present(alert, animated: true, completion: nil)
        case 4:
            //create a UIAlertController object
            let alert=UIAlertController(title: "Too many subs", message: "This team has already used 15 subs. Alert 2nd Ref.", preferredStyle: UIAlertControllerStyle.alert)
            //create UI alert action to the alert object
            var okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {action in textField.text=""})
            let length = (allPos[position]?.count)!
            if length > 0 {
                okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {action in textField.text=String(self.allPos[position]![length-1])})
            }
            
            //add alert to alert object
            alert.addAction(okAction)
            //present alert
            present(alert, animated: true, completion: nil)
        case 5:
            //create a UIAlertController object
            let alert=UIAlertController(title: "Illegal Libero Sub", message: "The libero can not go into this position. ", preferredStyle: UIAlertControllerStyle.alert)
            //create UI alert action to the alert object
            var okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {action in textField.text=""})
            let length = (allPos[position]?.count)!
            if length > 0 {
                okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {action in textField.text=String(self.allPos[position]![length-1])})
            }
            
            //add alert to alert object
            alert.addAction(okAction)
            //present alert
            present(alert, animated: true, completion: nil)
        case 6:
            //create a UIAlertController object
            let alert=UIAlertController(title: "Illegal Libero Sub", message: "The player that was in before the libero needs to go in before a sub can take place. Alert 2nd ref. ", preferredStyle: UIAlertControllerStyle.alert)
            //create UI alert action to the alert object
            var okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {action in textField.text=""})
            let length = (allPos[position]?.count)!
            if length > 0 {
                let s : String
                let last = self.allPos[position]![length-1]
                if team == 1{
                    if last == Team1L{
                        s = "L"
                    }
                    else{
                        s = "R"
                    }
                }
                else{
                    if last == Team2L{
                        s = "L"
                    }
                    else{
                        s = "R"
                    }
                }
                okAction = UIAlertAction(title: "OK", style: UIAlertActionStyle.default, handler: {action in textField.text=s})
            }
            
            //add alert to alert object
            alert.addAction(okAction)
            //present alert
            present(alert, animated: true, completion: nil)
        default:
            return
        }
        return
    }
    

    @IBAction func tapRecognized(_ sender: Any) {
        Pos1Text.resignFirstResponder()
        Pos2Text.resignFirstResponder()
        Pos3Text.resignFirstResponder()
        Pos4Text.resignFirstResponder()
        Pos5Text.resignFirstResponder()
        Pos6Text.resignFirstResponder()
        liberoText.resignFirstResponder()
        rLiberoText.resignFirstResponder()
    }
    
    func textFieldDidEndEditing(_ textField: UITextField) {
        var team: Int = 1
        if teamSegmentControl.selectedSegmentIndex == 1{
            team = 2
        }
        var position: Int = -1
        var player: Int!
        if !textField.text!.isEmpty{
            switch textField{
            case Pos1Text: position = 0*team // TODO update do that it works for the two teams
            case Pos2Text: position = 1*team
            case Pos3Text: position = 2*team
            case Pos4Text: position = 3*team
            case Pos5Text: position = 4*team
            case Pos6Text: position = 5*team
            case liberoText:
                if !(textField.text!.isEmpty){
                    setLiberos("L", team, Int(textField.text!)!)
                    return
                }
            case rLiberoText:
                if !(textField.text!.isEmpty){
                    setLiberos("R", team, Int(textField.text!)!)
                    return
                }
            default: return
            }
            var lib: Int! = Team1L
            var rlib: Int! = Team1R
            var res: Bool = true
            if (team==2){
                lib = Team2L
                rlib = Team2R
            }
            if (textField.text!) == "L" || (textField.text!) == "l"{
                if  lib != nil{
                    res = updateLibero(position, team, lib)
                }
                else{
                    //Throw error that there is no libero
                    throwErrorPopup(textField, 1, position, team)
                    return
                }
            }
            else if (lib != nil) && ((textField.text!) == String (lib)){
                    res = updateLibero(position, team, lib)
            }
            else if (textField.text!) == "R" || (textField.text!) == "r"{
                if  rlib != nil{
                    res = updateLibero(position, team,rlib)
                }
                else{
                    //Throw error that there is no libero
                    throwErrorPopup(textField, 2, position, team)
                    return
                }
            }
            else if (rlib != nil) && ((textField.text!) == String (rlib)){
                 res = updateLibero(position, team, rlib)
                
            }
            else{
                player = Int(textField.text!)!
                if subs[team-1] < 15{
                    let success = updateSubs(position, player,team)
                    if (success == 1){
                        subNumLabel.text=String(subs[team-1])
                        return
                    }
                    else{
                        
                        //Throw error that player has already played in position
                        if (success==2){
                            throwErrorPopup(textField, 3, position, team)
                            return
                        }
                        else{
                            throwErrorPopup(textField, 6, position, team)
                            return
                        }
                    }
                }
                else{
                    //Throw error that too many subs have occured
                    throwErrorPopup(textField, 4, position, team)
                    return
                }
                
            }
            if (!res){throwErrorPopup(textField, 5, position,team)}
        }
        
    }
    
    @IBAction func unwindSegue(_ segue: UIStoryboardSegue){
        
    }
    
    
    override func viewDidLoad() {
       
        Pos6Text.delegate=self
        Pos5Text.delegate=self
        Pos4Text.delegate=self
        Pos3Text.delegate=self
        Pos2Text.delegate=self
        Pos1Text.delegate=self
        liberoText.delegate=self
        rLiberoText.delegate=self
         super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

