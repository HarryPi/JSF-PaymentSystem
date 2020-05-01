/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  harry
 * Created: 30-Apr-2020
 */

INSERT INTO SYSTEMUSER (ID, "NAME", USERPASSWORD, USERNAME) (SELECT 1, 'admin1', '25f43b1486ad95a1398e3eeb3d83bc4010015fcc9bedb35b432e00298d5021f7', 'admin1' FROM SYSTEMUSER WHERE USERNAME = 'admin1' having count(*) = 0)
INSERT INTO SYSTEMUSERGROUP (ID, GROUPNAME, USERNAME) (SELECT 1, 'admins', 'admin1' FROM SYSTEMUSERGROUP WHERE ID = 1 HAVING count(*) = 0)