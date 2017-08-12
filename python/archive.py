#!/usr/bin/env python

from __future__ import print_function

import os
import platform
import subprocess
import zipfile

PIP = 'pip.exe' if platform.system() == 'Windows' else 'pip'


def zip_dir(path, ziph):
    for root, dirs, files in os.walk(path):
        for file in files:
            source = os.path.join(root, file)
            destination = os.path.sep.join(source.split(os.path.sep)[1:])
            ziph.write(source, destination)


if __name__ == '__main__':
    print('Installing dependencies into libs directory...')
    subprocess.call([PIP, 'install', '-r', 'requirements.txt', '-t', 'libs'],
                    stdout=subprocess.PIPE)

    print('Generating archive.zip...')
    with zipfile.ZipFile('archive.zip', 'w', zipfile.ZIP_DEFLATED) as zipf:
        zip_dir('libs%s' % os.path.sep, zipf)
        zipf.write('index.py')
