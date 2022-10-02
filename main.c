#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include<unistd.h>

char my_intepreter(FILE* file , char passedIN[])
{
    fseek(file,0,SEEK_END);
    int source_len=ftell(file);
    rewind(file);

    char myTape[1000000];
    int myPointer = 0;

    char buf[100];

    char returnArray[100000];
    int position = 0;
    while( fscanf(file, "%s", buf) != EOF )
    {
        if(strcmp(buf , "meow") == 0 ){myTape[myPointer]++;}
        else if(strcmp(buf , "mEEOw") == 0 )
        {
            myTape[myPointer] += 5;
        }
        else if(strcmp(buf , "MEOW") == 0 ){myTape[myPointer]--;}
        else if(strcmp(buf , "MeoW") == 0 ){myPointer++;}
        else if(strcmp(buf , "left") == 0 ){myPointer--;}
        else if(strcmp(buf , "nya") == 0 ){
            
            printf("->%d<-" , myTape[myPointer]);

            char c = myTape[myPointer];
            
            passedIN[position] = c;
            position++;
            
        } 




    }
    //passedIN = &returnArray[0];
} 

int main(int argc,char** argv)
{

    FILE* file;
    do
    {
        file = fopen("text.meow","r");
        printf("trying to find file\n");
        sleep(4);
    }while(!file);


    char c[10000]; 
    
    my_intepreter(file , c);

    printf("\nFOUND STRING == |%s|" , c);

    FILE * sendTO = fopen("pythonMessage.txt", "w");
    fprintf(sendTO,"%s",c);
    fclose(sendTO);

    return 1;
}