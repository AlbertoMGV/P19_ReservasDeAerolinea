<?php


$access_token = 'TU ACCESS_TOKEN';  //código de acceso a la API de Flickr (secreto).
$base_url = 'https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key='.$access_token.'&format=json&nojsoncallback=1&tags='; //url base para la búsqueda de imágenes en flickr (flickr.photos.search)
$aircraft_data = file_get_contents("aircraft.dat"); //leemos listado de aviones
$aircraft_array = explode(',', $aircraft_data); //separamos el listado en un array

//inicializacion del curl multithread.
$curl_arr = array();
$master = curl_multi_init();
//inicializar cada peticion
foreach ($aircraft_array as $key => $aircraft_name) {
	$trimmed = trim($aircraft_name);
	$search_url = $base_url . $trimmed;
	$curl_arr[$key] = curl_init($search_url);
	curl_setopt($curl_arr[$key], CURLOPT_RETURNTRANSFER, true);
	curl_multi_add_handle($master, $curl_arr[$key]);
}
//ejecutar curl.
do{
	curl_multi_exec($master, $running);
}while($running > 0);

//procesar respuesta de las peticiones
foreach($aircraft_array as $key => $aircraft_name){
	$trimmed = trim($aircraft_name);
	$json_data = curl_multi_getcontent($curl_arr[$key]);
	download_images($json_data, $trimmed); //descargar imágenes de cada avion
}

//descarga las imágenes de Flickr.

function download_images($json_data, $aircraft_name){
	$pathname = 'D:/xampp/htdocs/deusto/imagenes/'.$aircraft_name.'_images/';
	$data_array = json_decode($json_data);
	$photos = $data_array->photos;
	$links = get_download_links($photos);
	$curl_arr = array();
	$master = curl_multi_init();
	mkdir($pathname);
	foreach ($links as $key => $link) {
		$curl_arr[$key] = curl_init($link);
		curl_setopt($curl_arr[$key], CURLOPT_RETURNTRANSFER, true);
		curl_multi_add_handle($master, $curl_arr[$key]);
	}

	do{
		curl_multi_exec($master, $running);
	}while($running > 0);

	foreach ($links as $key => $link) {
		$filename = $pathname.substr($link, strlen($link) - 26, 26);
		file_put_contents($filename, curl_multi_getcontent($curl_arr[$key]));
	}

}


//accede a la API de Flickr para obtener los links de descarga a partir de sus ID.

function get_download_links($photos){
	$base_api_url = 'https://api.flickr.com/services/rest/?method=flickr.photos.getSizes&api_key='.$access_token.'&format=json&nojsoncallback=1&photo_id=';
	$index = 0;
	$curl_arr = array();
	$master = curl_multi_init();
	$links = array();
	foreach ($photos->photo as $key => $photo) {
		if($index < 10){
			$api_url = $base_api_url.$photo->id;
			$curl_arr[$key] = curl_init($api_url);
			curl_setopt($curl_arr[$key], CURLOPT_RETURNTRANSFER, true);
			curl_multi_add_handle($master, $curl_arr[$key]);
		}
	}do {
    curl_multi_exec($master,$running);
		} while($running > 0);

		foreach ($photos->photo as $key => $photo) {
			if($index < 5){
				$api_data = curl_multi_getcontent  ($curl_arr[$key]);
				$api_array = json_decode($api_data);
				$links[] = $api_array->sizes->size[5]->source;
				$index++;
			}

	}
	return $links;
}


?>
