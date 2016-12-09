<?php


header('Content-Type: application/json');

class DatabaseAdaptor {
	
	private $db;
	
	public function __construct() {
		
		try{
			$ini = 'mysql:dbname=imdb;charset=utf8;host=127.0.0.1';
			$this->db = new PDO($ini, 'root');
			$this->db->setAttribute (PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		} catch (PDOException $e){
			echo ('Andrew writes: Error establishing Connection!!');
			exit();
		}
	}
	
	public function getJSON($movie, $firstName, $lastName){
		if ($movie == null && $firstName != null && $lastName != null){
			$stmt = $this->db->prepare("SELECT m.name, a.first_name, a.last_name, m.year FROM actors a JOIN roles r ON r.actor_id = a.id JOIN movies m ON m.id = r.movie_id WHERE a.first_name LIKE :firstName '%' AND a.last_name LIKE :lastName '%' LIMIT 20");
			$stmt->bindParam('firstName', $firstName);
			$stmt->bindParam('lastName', $lastName);
			$stmt->execute();
			echo json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));
		}
		if ($firstName == null && $movie != null && $lastName != null){
			$stmt = $this->db->prepare("SELECT m.name, a.first_name, a.last_name, m.year FROM actors a JOIN roles r ON r.actor_id = a.id JOIN movies m ON m.id = r.movie_id WHERE m.name LIKE :movie '%' AND a.last_name LIKE :lastName '%' LIMIT 20");
			$stmt->bindParam('movie', $movie);
			$stmt->bindParam('lastName', $lastName);
			$stmt->execute();
			echo json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));
		}
		if ($lastName == null && $movie != null && $firstName != null){
			$stmt = $this->db->prepare("SELECT m.name, a.first_name, a.last_name, m.year FROM actors a JOIN roles r ON r.actor_id = a.id JOIN movies m ON m.id = r.movie_id WHERE m.name LIKE :movie '%' AND a.first_name LIKE :firstName '%' LIMIT 20");
			$stmt->bindParam('firstName', $firstName);
			$stmt->bindParam('movie', $movie);
			$stmt->execute();
			echo json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));
		}
		if ($movie == null && $firstName == null && $lastName != null){
			$stmt = $this->db->prepare("SELECT m.name, a.first_name, a.last_name, m.year FROM actors a JOIN roles r ON r.actor_id = a.id JOIN movies m ON m.id = r.movie_id WHERE a.last_name LIKE :lastName '%' LIMIT 20");
			$stmt->bindParam('lastName', $lastName);
			$stmt->execute();
			echo json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));
		}
		if ($movie == null && $lastName == null && $firstName != null){
			$stmt = $this->db->prepare("SELECT m.name, a.first_name, a.last_name, m.year FROM actors a JOIN roles r ON r.actor_id = a.id JOIN movies m ON m.id = r.movie_id WHERE a.first_name LIKE :firstName '%' LIMIT 20");
			$stmt->bindParam('firstName', $firstName);
			$stmt->execute();
			echo json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));
		}
		if ($movie != null && $lastName == null && $firstName == null){
			$stmt = $this->db->prepare("SELECT m.name, a.first_name, a.last_name, m.year FROM actors a JOIN roles r ON r.actor_id = a.id JOIN movies m ON m.id = r.movie_id WHERE m.name LIKE :movie '%' LIMIT 20");
			$stmt->bindParam('movie', $movie);
			$stmt->execute();
			echo json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));
		}
		if ($movie != null && $firstName != null && $lastName != null){
			$stmt = $this->db->prepare("SELECT m.name, a.first_name, a.last_name, m.year FROM actors a JOIN roles r ON r.actor_id = a.id JOIN movies m ON m.id = r.movie_id WHERE m.name LIKE :movie '%' AND a.first_name LIKE :firstName '%' AND a.last_name LIKE :lastName '%' LIMIT 20");
			$stmt->bindParam('movie', $movie);
			$stmt->bindParam('firstName', $firstName);
			$stmt->bindParam('lastName', $lastName);
			$stmt->execute();
			echo json_encode($stmt->fetchAll(PDO::FETCH_ASSOC));
		}
	}
}

	$myDatabaseFunction = new DatabaseAdaptor();
	$myDatabaseFunction->getJSON($_POST['title'], $_POST['actorFirst'], $_POST['actorLast']);
?>