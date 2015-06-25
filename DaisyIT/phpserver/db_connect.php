<?php

/**
 * A class file to connect to database
 */
class DB_CONNECT {

	private $con;
    // constructor
    function __construct() {
        // connecting to database
        $this->connect();
    }

    // destructor
    function __destruct() {
        // closing db connection
        $this->close();
    }

    /**
     * Function to connect with database
     */
    function connect() {
        require_once __DIR__ . '/db_config.php';
		$this->con = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD) or die(mysqli_error($this->con));
        if(mysqli_errno($this->con)){
            echo("Connect to Mysql Server error!!!\r\t");
        }
        else{
            echo("Connect to Mysql Server success!!!\r\t");
        }
		$db = mysqli_select_db($this->con, DB_DATABASE) or die(mysqli_error($this->con));
        if($db){
            echo("Connect to database success!!!!!!\r\t");
        }
        else{
            echo("Connect to database error!!!!!!\r\t");
        }
    }

    /**
     * Function to close db connection
     */
    function close() {
        // closing db connection
        mysqli_close($this->con);
    }

	function getConnection() { 
		return $this->con; 
	}
}

?>