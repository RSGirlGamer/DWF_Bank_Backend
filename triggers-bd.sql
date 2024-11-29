/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 8.2.0 : Database - bank_dwf
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `bank_dwf`;

/* Trigger structure for table `client_lender` */

DELIMITER $$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `set_init_credit_limit` BEFORE INSERT ON `client_lender` FOR EACH ROW BEGIN
    -- Insertar en tabla de audit
    INSERT INTO trigger_init_credit_log (trigger_name, execution_time,client_id,salary)
    VALUES ('credit_init',NOW(), New.id, NEW.salary);
    IF NEW.role = 'Cliente' THEN
	IF NEW.salary < 365 THEN
		SET NEW.credit_limit = 10000;
	ELSEIF NEW.salary < 600 THEN
		SET NEW.credit_limit = 25000;
	elseif NEW.salary< 900 THEN
		SET NEW.credit_limit = 35000;
	ELSE
		SET NEW.credit_limit = 50000;
	END IF;
   
    END IF;
    END */$$


DELIMITER ;

/* Trigger structure for table `movements` */

DELIMITER $$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `prueba` AFTER INSERT ON `movements` FOR EACH ROW BEGIN
    -- Insertar en tabla de audit
    INSERT INTO trigger_logs (execution_time, trigger_name,description,  movement_type)
    VALUES (NOW(), 'prueba', New.description, NEW.type);
    
     IF NEW.type = 'transfer_out' THEN
        -- Restar el monto de la cuenta transmisora
        UPDATE bank_dwf.account
        SET account.balance= account.balance- NEW.amount
        WHERE account.id= NEW.account_transmitter_id;
        
        -- Sumar el monto a la cuenta receptora
        UPDATE bank_dwf.account
        SET account.balance= account.balance+ NEW.amount
        WHERE account.id= NEW.account_receiver_id;
                
     ELSEIF NEW.type = 'deposit' THEN
        -- Sumar el monto a la cuenta receptora
        UPDATE bank_dwf.account
        SET account.balance= account.balance+ NEW.amount
        WHERE account.id = NEW.account_receiver_id;
     ELSEIF NEW.type = 'withdrawal' THEN
        -- Restar el monto de la cuenta transmisora
        UPDATE bank_dwf.account
        SET account.balance= account.balance- NEW.amount
        WHERE account.id = NEW.account_transmitter_id;
      END IF;
     
    END */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
