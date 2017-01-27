from airflow import DAG
from airflow.operators import BashOperator
from datetime import datetime
import os
import sys

args = {
  'owner': 'airflow'
  , 'start_date': datetime(2017, 1, 27)
  , 'provide_context': True
}
d = datetime(2017, 1, 17)
d.time(3,15,00)

dag = DAG(
  'kafka_run-jar'
  , start_date = 
  , schedule_interval = '@hourly'
  , default_args = args
)

t_main = BashOperator(
  task_id = 'run_kafka_jar'
  , dag = dag
  bash_command = 'java -cp kafkaUSGS-1.0-SNAPSHOT.jar KafkaUSGS'
  #params = {'class': 'FetchJSON', 'path': 'jars/kafkaUSGS.jar'}
  )


  
